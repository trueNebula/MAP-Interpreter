package repository;
import model.collections.dictionary.GenericDictionary;
import model.collections.dictionary.IDictionary;
import model.collections.heap.GenericHeap;
import model.collections.heap.IHeap;
import model.collections.list.GenericList;
import model.collections.list.IList;
import model.collections.stack.GenericStack;
import model.collections.stack.IStack;
import model.exceptions.LoggingException;
import model.statements.*;
import model.structures.ProgramState;
import model.values.IValue;
import model.values.StringValue;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    List<ProgramState> programStateList = new ArrayList<>();
    public IStatement originalProgram;
    String logFilePath;

    public Repository(IStatement program, String logPath){

        // create Structures
        IStack<IStatement> exeStack = new GenericStack<>();
        IDictionary<String, IValue> symTable = new GenericDictionary<>();
        IList<IValue> out = new GenericList<>();
        IDictionary<StringValue, BufferedReader> fTable = new GenericDictionary<>();
        IHeap heap = new GenericHeap();

        originalProgram = program;
        logFilePath = logPath;

        programStateList.add(new ProgramState(exeStack, symTable, out, fTable, heap, program));
        ProgramState.initID();
        programStateList.get(0).setID();

    }

    public List<ProgramState> getProgramStateList(){
        return programStateList;

    }

    public void setProgramStateList(List<ProgramState> programStateList){
        this.programStateList = programStateList;

    }

    public void logProgramState(ProgramState state) throws LoggingException{
        try {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));

            // separate from previous logs
            logFile.println("---------------");
            logFile.println("LOG START\n");

            // print Program State ID
            logFile.println("ID: " + state.getID() + "\n");

            // print the Execution Stack text and its contents inorder
            logFile.println("ExeStack:");
            logFile.close();
            logExeStackInorderWrapper(state);

            // open the writer again and clean up
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            logFile.println();

            // print the other structures
            logFile.println("Symbol Table:");
            logFile.print(state.getSymbolTable());
            logFile.println();

            logFile.println("Output Stream:");
            logFile.print(state.getOutputStream());
            logFile.println();

            logFile.println("File Table:");
            logFile.print(state.getFileTable());
            logFile.println();

            logFile.println("Heap Table:");
            logFile.print(state.getHeapTable());
            logFile.println();

            // finish up
            logFile.println("LOG END");
            logFile.println("---------------");
            logFile.print("\n\n");

            logFile.close();

        }

        catch (IOException IOE) {
            throw new LoggingException(IOE.getMessage());

        }

    }

    public void logExeStackInorderWrapper(ProgramState state) throws LoggingException {
        IStack<IStatement> exeStack = state.getExecutionStack();

        if (!exeStack.isEmpty())
            logExeStackInorder(exeStack.peek());

    }

    public void logExeStackInorder(IStatement statement) throws LoggingException {
        try {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));

            if (statement instanceof CompoundStatement)
                logExeStackInorder(((CompoundStatement) statement).getFirst());

            if (!(statement instanceof CompoundStatement))
                logFile.println(statement);

            if (statement instanceof CompoundStatement)
                logExeStackInorder(((CompoundStatement) statement).getSecond());

            logFile.close();

        }

        catch(IOException IOE) {
            throw new LoggingException(IOE.getMessage());

        }

    }

}
