package model.statements;

import model.collections.dictionary.IDictionary;
import model.exceptions.ExpressionEvaluationException;
import model.exceptions.StatementExecutionException;
import model.exceptions.TypeCheckException;
import model.structures.ProgramState;
import model.types.IType;

public interface IStatement {
    // execution method for the given statement
    ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException;
    IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnv) throws TypeCheckException;


}
