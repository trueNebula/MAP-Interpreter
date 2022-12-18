package model.statements;

import model.collections.dictionary.IDictionary;
import model.collections.heap.IHeap;
import model.exceptions.ExpressionEvaluationException;
import model.exceptions.StatementExecutionException;
import model.expressions.IExpression;
import model.structures.ProgramState;
import model.types.IntType;
import model.values.IValue;
import model.values.IntValue;
import model.values.StringValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

@SuppressWarnings("unused")
public class ReadFile implements IStatement{
    IExpression expr;
    StringValue varName;

    public ReadFile(IExpression e, StringValue name){
        expr = e;
        varName = name;

    }

    @Override
    public String toString() {
        return "readFile(" + expr + ", " + varName +  ");";

    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException {
        IDictionary<String, IValue> symTable = state.getSymbolTable();
        IDictionary<StringValue, BufferedReader> fTable = state.getFileTable();
        IHeap heapTable = state.getHeapTable();
        IValue variable = symTable.get(varName.getValue());

        if(variable == null)
            throw new StatementExecutionException("Variable " + varName + " not defined!");

        if(!Objects.equals(variable.getType(), new IntType()))
            throw new StatementExecutionException("Variable " + varName + " not of int type!");

        IValue evalResult = expr.evaluate(symTable, heapTable);
        BufferedReader fileBR = fTable.get((StringValue)evalResult);

        if(fileBR == null){
            throw new StatementExecutionException("Cannot access file " + evalResult);

        }

        try {
            String line = fileBR.readLine();
            IValue readValue;

            if(line != null)
                readValue = new IntValue(Integer.parseInt(line));

            else
                readValue = new IntType().defaultValue();

            symTable.put(varName.getValue(), readValue);

        }

        catch (IOException IOE){
            throw new StatementExecutionException(IOE.getMessage());

        }

        return null;


    }

}
