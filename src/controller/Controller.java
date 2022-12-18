package controller;
import model.collections.list.GenericList;
import model.exceptions.CollectionException;
import model.exceptions.ExpressionEvaluationException;
import model.exceptions.LoggingException;
import model.exceptions.StatementExecutionException;
import model.structures.GarbageCollector;
import model.structures.ProgramState;
import model.values.IValue;
import repository.Repository;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;


public class Controller {
    Repository repository;
    ExecutorService executor;

    public Controller(Repository repo){
        repository = repo;

    }

    public List<ProgramState> removeCompletedPrograms(List<ProgramState> inProgramList){
        return inProgramList.stream()
                .filter(ProgramState::isNotCompleted)
                .collect(Collectors.toList());

    }

    public void runOneStepForAllThreads(List<ProgramState> programStateList) throws LoggingException, InterruptedException {
        // log the curernt ProgramState
        for (ProgramState programState : programStateList) {
            repository.logProgramState(programState);
        }

        // create the list of Callables (one per ProgramState to execute threads concurrently
        List<Callable<ProgramState>> callableList = programStateList.stream()
                .map((ProgramState p) -> (Callable<ProgramState>)(p::runOneStep))
                .toList();

        // execute the Callables
        List<ProgramState> newProgramStateList = executor.invokeAll(callableList).stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException IE) {
                        System.out.println("Caught InterruptedException:");
                        System.out.println(IE.getMessage() + "\n");
                    } catch (ExecutionException EE) {
                        System.out.println("Caught ExecutionException:");
                        System.out.println(EE.getMessage() + "\n");
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .toList();

        // add the newly created threads to the list of existing threads
        programStateList.addAll(newProgramStateList);
        repository.setProgramStateList(programStateList);

    }

    public void runAllSteps() throws StatementExecutionException, ExpressionEvaluationException, CollectionException, LoggingException, InterruptedException {
        executor = Executors.newFixedThreadPool(2);

        // remove the completed threads
        List<ProgramState> programStateList = removeCompletedPrograms(repository.getProgramStateList());

        while(programStateList.size() > 0){
            // run garbage collector
            GarbageCollector.threadCollect(programStateList);

            // run one step for all threads
            runOneStepForAllThreads(programStateList);

            // print any output
            programStateList.forEach(p -> {if(!p.getOutputStream().isEmpty()) printOutput(p); });

            // remove all completed threads from the list
            programStateList = removeCompletedPrograms(repository.getProgramStateList());

        }

        executor.shutdownNow();
        repository.setProgramStateList(programStateList);

    }

    @SuppressWarnings("unused")
    public List<ProgramState> getProgramStateList(){
        return repository.getProgramStateList();

    }

    @SuppressWarnings("unused")
    public Repository getRepository(){
        return repository;

    }


    public void printOutput(ProgramState prgState){
        GenericList<IValue> out = (GenericList<IValue>) prgState.getOutputStream();

        for(IValue i : out){
            System.out.println(i.toString());

        }

        out.clear();

    }



}
