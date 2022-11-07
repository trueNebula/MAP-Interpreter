package Model.Statements;

import Model.Exceptions.ExpressionEvaluationException;
import Model.Exceptions.StatementExecutionException;
import Model.Structures.ProgramState;

public interface IStatement {
    // execution method for the given statement
    ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException;


}
