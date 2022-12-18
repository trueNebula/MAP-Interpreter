package model.statements;

import model.collections.dictionary.IDictionary;
import model.collections.heap.IHeap;
import model.exceptions.ExpressionEvaluationException;
import model.exceptions.StatementExecutionException;
import model.expressions.IExpression;
import model.structures.ProgramState;
import model.types.StringType;
import model.values.IValue;
import model.values.StringValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

@SuppressWarnings("unused")
public class CloseRFile implements IStatement{
    IExpression expr;

    public CloseRFile(IExpression e){
        expr = e;

    }


    @Override
    public String toString() {
        return "closeRFile(" + expr + ");";

    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException {
        IDictionary<String, IValue> symTable = state.getSymbolTable();
        IHeap heap = state.getHeapTable();
        IDictionary<StringValue, BufferedReader> fTable = state.getFileTable();
        IValue evalResult = expr.evaluate(symTable, heap);

        if(!Objects.equals(evalResult.getType(), new StringType()))
            throw new StatementExecutionException("Expression is not of String Type!");

        BufferedReader fileBR = fTable.get((StringValue)evalResult);

        if(fileBR == null)
            throw new StatementExecutionException("File Reader " + evalResult + " not found!");

        try {
            fileBR.close();
            fTable.remove((StringValue)evalResult);

        }

        catch(IOException IOE){
            throw new StatementExecutionException("Cannot close File Reader " + evalResult + "! (Already closed?)");

        }

        return null;

    }

}
