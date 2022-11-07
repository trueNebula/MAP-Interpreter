package Model.Statements;

import Model.Exceptions.ExpressionEvaluationException;
import Model.Exceptions.StatementExecutionException;
import Model.Structures.ProgramState;

public class NoStatement implements IStatement{

    public String toString(){
        return "nop; ";

    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException {
        return state;

    }

}
