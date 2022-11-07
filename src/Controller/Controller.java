package Controller;
import Model.Collections.Dictionary.IDictionary;
import Model.Collections.Stack.IStack;
import Model.Exceptions.StatementExecutionException;
import Model.Statements.IStatement;
import Model.Structures.ProgramState;
import Model.Values.IValue;
import Repo.Repository;

public class Controller {
    Repository repository;

    public Controller(Repository repo){
        repository = repo;

    }

    public ProgramState runOneStep(ProgramState state) throws StatementExecutionException {
        IStack<IStatement> exeStack = state.getExecutionStack();
        IDictionary<String, IValue> symTable = state.getSymbolTable();


        IStatement currentStatement = exeStack.pop();

        try {
            currentStatement.execute(state);

        }

        catch (StatementExecutionException SEE) {
            throw SEE;

        }

        return state;

    }

    void runAllSteps() throws StatementExecutionException{
        ProgramState progState = repository.getCurrentProgramState();

        while(!progState.getExecutionStack().isEmpty()){
            runOneStep(progState);
            // maybe display some stuff

        }

    }

}
