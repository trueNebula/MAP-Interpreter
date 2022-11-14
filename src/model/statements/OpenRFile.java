package model.statements;

import model.collections.dictionary.IDictionary;
import model.exceptions.ExpressionEvaluationException;
import model.exceptions.StatementExecutionException;
import model.expressions.IExpression;
import model.structures.ProgramState;
import model.types.StringType;
import model.values.IValue;
import model.values.StringValue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Objects;

public class OpenRFile implements IStatement{
    IExpression expr;

    public OpenRFile(IExpression e){
        expr = e;

    }


    @Override
    public String toString() {
        return "openRFile(" + expr + ");";

    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException {
        IDictionary<String, IValue> symTable = state.getSymbolTable();
        IDictionary<StringValue, BufferedReader> fTable = state.getFileTable();
        IValue evalResult = expr.evaluate(symTable);

        if(!Objects.equals(evalResult.getType(), new StringType()))
            throw new StatementExecutionException("Expression is not of String Type!");

        if(symTable.get(evalResult.toString()) != null)
            throw new StatementExecutionException("File path variable already exists!");

        try{
            BufferedReader fileBR = new BufferedReader(new FileReader(((StringValue)evalResult).getValue()));
            fTable.put((StringValue)evalResult, fileBR);

        }

        catch (FileNotFoundException FNFE){
            throw new StatementExecutionException("File not found!");

        }

        return state;

    }

}
