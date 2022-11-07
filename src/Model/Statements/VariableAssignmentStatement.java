package Model.Statements;

import Model.Collections.Dictionary.IDictionary;
import Model.Collections.Stack.IStack;
import Model.Exceptions.ExpressionEvaluationException;
import Model.Exceptions.StatementExecutionException;
import Model.Expressions.IExpression;
import Model.Structures.ProgramState;
import Model.Types.IType;
import Model.Values.IValue;


public class VariableAssignmentStatement implements IStatement{
    String id;
    IExpression expr;

    public VariableAssignmentStatement(String s, IExpression e){
        id = s;
        expr = e;

    }


    public String toString() {
        return id + " = " + expr.toString();

    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException {
        IStack<IStatement> exeStack = state.getExecutionStack();
        IDictionary<String, IValue> symTable = state.getSymbolTable();

        if(symTable.get(id) != null){
            try {
                IValue value = expr.evaluate(symTable);
                IType type = (symTable.get(id)).getType();

                if(value.getType().equals(type)){
                    symTable.put(id, value);

                }

                else{
                    throw new StatementExecutionException("Declared type of variable " + id + " and type of assigned expression do not match");

                }

            }

            catch(ExpressionEvaluationException EEException){
                throw new StatementExecutionException(EEException.getMessage());

            }

        }

        else{
            throw new StatementExecutionException("The used variable " + id + " was not assigned before");

        }

        return state;

    }

}
