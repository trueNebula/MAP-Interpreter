package Model.Structures;
import Model.Collections.Dictionary.*;
import Model.Collections.List.*;
import Model.Collections.Stack.*;
import Model.Statements.IStatement;
import Model.Values.IValue;

public class ProgramState {
    IStack<IStatement> executionStack;
    IDictionary<String, IValue> symbolTable;
    IList<IValue> outputStream;
    IStatement originalProgram;

    public ProgramState(IStack<IStatement> exeStack, IDictionary<String, IValue> symTable, IList<IValue> out, IStatement original){
        executionStack = exeStack;
        symbolTable = symTable;
        outputStream = out;
        originalProgram = original;

        exeStack.push(originalProgram);

    }

    @Override
    public String toString(){
        return "";

    }


    // Getters

    public IStack<IStatement> getExecutionStack(){
        return executionStack;

    }

    public IDictionary<String, IValue> getSymbolTable(){
        return symbolTable;

    }

    public IList<IValue> getOutputStream(){
        return outputStream;

    }

    public IStatement getOriginalProgram(){
        return originalProgram;

    }


    // Setters

    public void setExecutionStack(IStack<IStatement> exeStack){
        executionStack = exeStack;

    }

    public void setSymbolTable(IDictionary<String, IValue> symTable){
        symbolTable = symTable;

    }

    public void setOutputStream(IList<IValue> out){
        outputStream = out;

    }

    public void setOriginalProgram(IStatement original){
        originalProgram = original;

    }

}
