package model.structures;
import model.collections.dictionary.*;
import model.collections.list.*;
import model.collections.stack.*;
import model.statements.IStatement;
import model.values.IValue;
import model.values.StringValue;

import java.io.BufferedReader;

public class ProgramState {
    IStack<IStatement> executionStack;
    IDictionary<String, IValue> symbolTable;
    IList<IValue> outputStream;
    IStatement originalProgram;

    IDictionary<StringValue, BufferedReader> fileTable;

    public ProgramState(IStack<IStatement> exeStack, IDictionary<String, IValue> symTable, IList<IValue> out, IDictionary<StringValue, BufferedReader> fTable, IStatement original){
        executionStack = exeStack;
        symbolTable = symTable;
        outputStream = out;
        fileTable = fTable;
        originalProgram = original;

        exeStack.push(originalProgram);

    }

    @Override
    public String toString(){
        return "ExeStack: \n" + executionStack.toString() + "\n-------------\n\nSymbol Table: \n"
                + symbolTable.toString() + "\n-------------\n\nOutput Stream: \n"
                + outputStream.toString() + "\n-------------\n";

    }


    // Getters

    @SuppressWarnings("unused")
    public IStack<IStatement> getExecutionStack(){
        return executionStack;

    }

    @SuppressWarnings("unused")
    public IDictionary<String, IValue> getSymbolTable(){
        return symbolTable;

    }

    @SuppressWarnings("unused")
    public IList<IValue> getOutputStream(){
        return outputStream;

    }

    @SuppressWarnings("unused")
    public IDictionary<StringValue, BufferedReader> getFileTable(){
        return fileTable;

    }

    @SuppressWarnings("unused")
    public IStatement getOriginalProgram(){
        return originalProgram;

    }


    // Setters

    @SuppressWarnings("unused")
    public void setExecutionStack(IStack<IStatement> exeStack){
        executionStack = exeStack;

    }

    @SuppressWarnings("unused")
    public void setSymbolTable(IDictionary<String, IValue> symTable){
        symbolTable = symTable;

    }

    @SuppressWarnings("unused")
    public void setOutputStream(IList<IValue> out){
        outputStream = out;

    }

    @SuppressWarnings("unused")
    public void setFileTable(IDictionary<StringValue, BufferedReader> fTable){
        fileTable = fTable;

    }

    @SuppressWarnings("unused")
    public void setOriginalProgram(IStatement original){
        originalProgram = original;

    }

}
