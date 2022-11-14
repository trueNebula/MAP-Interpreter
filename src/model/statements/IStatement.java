package model.statements;

import model.exceptions.ExpressionEvaluationException;
import model.exceptions.StatementExecutionException;
import model.structures.ProgramState;

public interface IStatement {
    // execution method for the given statement
    ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException;


}
