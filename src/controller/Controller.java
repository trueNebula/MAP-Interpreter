package controller;
import model.collections.list.GenericList;
import model.collections.stack.IStack;
import model.exceptions.CollectionException;
import model.exceptions.ExpressionEvaluationException;
import model.exceptions.StatementExecutionException;
import model.statements.IStatement;
import model.structures.ProgramState;
import model.values.IValue;
import repository.Repository;

public class Controller {
    Repository repository;

    public Controller(Repository repo){
        repository = repo;

    }

    public ProgramState runOneStep(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException, CollectionException {
        IStack<IStatement> exeStack = state.getExecutionStack();

        if(exeStack.isEmpty())
            throw new StatementExecutionException("ExecutionStack is empty");

        IStatement currentStatement = exeStack.pop();
        currentStatement.execute(state);
        printProgramState(state);

        return state;

    }

    public void runAllSteps() throws StatementExecutionException, ExpressionEvaluationException, CollectionException{
        ProgramState progState = repository.getCurrentProgramState();

        while(!progState.getExecutionStack().isEmpty()){
            runOneStep(progState);
            // maybe display some stuff

            if(!progState.getOutputStream().isEmpty())
                printOutput();

        }

    }

    public ProgramState getCurrentProgramState(){
        return repository.getCurrentProgramState();

    }

    public void changeProgram(int i){
        repository.changeProgram(i);

    }

    public Repository getRepository(){
        return repository;

    }


    public void printOutput(){
        GenericList<IValue> out = (GenericList<IValue>) getCurrentProgramState().getOutputStream();

        for(IValue i : out){
            System.out.println(i.toString());

        }

        out.clear();

    }

    public void printProgramState(ProgramState state){
        System.out.println(state);

    }

}
