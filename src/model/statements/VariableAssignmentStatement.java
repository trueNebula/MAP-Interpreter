package model.statements;

import model.collections.dictionary.IDictionary;
import model.collections.heap.IHeap;
import model.exceptions.ExpressionEvaluationException;
import model.exceptions.StatementExecutionException;
import model.expressions.IExpression;
import model.structures.ProgramState;
import model.types.IType;
import model.values.IValue;


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
        IHeap heapTable = state.getHeapTable();

        if(symTable.get(id) != null) {
            IValue value = expr.evaluate(symTable, heapTable);
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
