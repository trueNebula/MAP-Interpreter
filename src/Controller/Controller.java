package Controller;
import Model.Collections.Stack.IStack;
import Model.Exceptions.CollectionException;
import Model.Exceptions.ExpressionEvaluationException;
import Model.Exceptions.StatementExecutionException;
import Model.Statements.IStatement;
import Model.Structures.ProgramState;
import Repo.Repository;

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

        return state;

    }

    public void runAllSteps() throws StatementExecutionException, ExpressionEvaluationException, CollectionException{
        ProgramState progState = repository.getCurrentProgramState();

        while(!progState.getExecutionStack().isEmpty()){
            runOneStep(progState);
            // maybe display some stuff

            if(!progState.getOutputStream().isEmpty())
                return;

        }

    }

    public ProgramState getCurrentProgramState(){
        return repository.getCurrentProgramState();

    }

}
