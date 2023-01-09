package model.statements;

import model.collections.dictionary.IDictionary;
import model.collections.heap.IHeap;
import model.exceptions.ExpressionEvaluationException;
import model.exceptions.StatementExecutionException;
import model.exceptions.TypeCheckException;
import model.expressions.IExpression;
import model.structures.ProgramState;
import model.types.IType;
import model.types.ReferenceType;
import model.values.IValue;
import model.values.ReferenceValue;

import java.util.Objects;

@SuppressWarnings("unused")
public class HeapWriteStatement implements IStatement{
    String variableName;
    IExpression expression;

    public HeapWriteStatement(String varName, IExpression expr){
        expression = expr;
        variableName = varName;

    }


    public String toString(){
        return "writeHeap(" + variableName + ", " + expression + ");";


    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException {
        IDictionary<String, IValue> symbolTable = state.getSymbolTable();
        IHeap heapTable = state.getHeapTable();

        // check if variable declared
        if(symbolTable.get(variableName) != null){
            IValue variableValue = symbolTable.get(variableName);

            // check if declared variable is of reference type
            if(variableValue.getType() instanceof ReferenceType variableType) {
                int variableAddress = ((ReferenceValue)variableValue).getAddress();

                // check if address in the reference variable is declared in the heap table
                if(heapTable.get(variableAddress) != null){
                    IValue expressionEvaluationResult = expression.evaluate(symbolTable, heapTable);

                    // check if expression result type is equal to variable inner type
                    if(Objects.equals(expressionEvaluationResult.getType(), variableType.getInner())){
                        heapTable.put(variableAddress, expressionEvaluationResult);

                    }

                    else{
                        throw new StatementExecutionException("Cannot assign type " + expressionEvaluationResult.getType() + " to reference to type " + variableType.getInner() + "!");

                    }

                }

                else{
                    throw new StatementExecutionException("Referenced address " + variableAddress + " not in heap table!");

                }

            }

            else{
                throw new StatementExecutionException("Variable " + variableName + " is not a reference!");

            }

        }

        else{
            throw new StatementExecutionException("Variable " + variableName + " not declared!");

        }

        return null;

    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnv) throws TypeCheckException {
        IType varType = typeEnv.get(variableName);
        IType expType = expression.typeCheck(typeEnv);

        if(!varType.equals(new ReferenceType(expType)))
            throw new TypeCheckException("writeHeap statement left hand side and right hand side of different types!");

        return typeEnv;

    }

}
