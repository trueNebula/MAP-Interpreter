package Model.Statements;

import Model.Collections.Dictionary.IDictionary;
import Model.Exceptions.ExpressionEvaluationException;
import Model.Exceptions.StatementExecutionException;
import Model.Expressions.IExpression;
import Model.Structures.ProgramState;
import Model.Types.IType;
import Model.Values.IValue;

@SuppressWarnings("unused")

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
    public ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException {
        IDictionary<String, IValue> symTable = state.getSymbolTable();

        if(symTable.get(id) != null) {
            IValue value = expr.evaluate(symTable);
            IType type = (symTable.get(id)).getType();

            if (value.getType().equals(type)) {
                symTable.put(id, value);

            } else {
                throw new StatementExecutionException("Declared type of variable " + id + " and type of assigned expression do not match");

            }


        }

        else{
            throw new StatementExecutionException("The used variable " + id + " was not assigned before");

        }

        return state;

    }

}
