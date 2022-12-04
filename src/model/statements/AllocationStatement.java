package model.statements;

import model.collections.dictionary.IDictionary;
import model.exceptions.ExpressionEvaluationException;
import model.exceptions.StatementExecutionException;
import model.expressions.IExpression;
import model.structures.ProgramState;
import model.types.ReferenceType;
import model.values.IValue;
import model.values.ReferenceValue;

import java.util.Objects;

public class AllocationStatement implements IStatement{
    String variableName;
    IExpression expression;

    public AllocationStatement(String variableName, IExpression expression){
        this.variableName = variableName;
        this.expression = expression;

    }

    @Override
    public String toString() {
        return "new(" + variableName + ", " + expression.toString() + ");";

    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException {
        IDictionary<String, IValue> symbolTable = state.getSymbolTable();
        IDictionary<Integer, IValue> heapTable = state.getHeapTable();

        // check if variable declared
        if(symbolTable.get(variableName) != null){
            IValue variableValue = symbolTable.get(variableName);

            // check if declared variable is of reference type
            if(variableValue.getType() instanceof ReferenceType variableType) {
                IValue exprressionEvalResult = expression.evaluate(symbolTable, heapTable);

                //
                if(Objects.equals(exprressionEvalResult.getType(), variableType.getInner())){
                    int heapAddress = 1;

                    while(heapTable.get(heapAddress) != null)
                        heapAddress++;

                    heapTable.put(heapAddress, exprressionEvalResult);
                    symbolTable.put(variableName, new ReferenceValue(heapAddress, variableType.getInner()));

                }

                else{
                    throw new StatementExecutionException("Trying to assign value of type " + exprressionEvalResult.getType() + " to reference to type " + variableType + "!");

                }

            }

            else{
                throw new StatementExecutionException("Variable " + variableName + " is not a reference!");

            }

        }

        else{
            throw new StatementExecutionException("Variable " + variableName + " was not declared before!");

        }

        return state;

    }

}
