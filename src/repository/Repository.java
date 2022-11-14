package repository;
import model.collections.dictionary.GenericDictionary;
import model.collections.dictionary.IDictionary;
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
    List<ProgramState> repo = new ArrayList<>();
    public IStatement originalProgram;
    String logFilePath;

    public Repository(IStatement program, String logPath){

        // create Structures
        IStack<IStatement> exeStack = new GenericStack<>();
        IDictionary<String, IValue> symTable = new GenericDictionary<>();
        IList<IValue> out = new GenericList<>();
        IDictionary<StringValue, BufferedReader> fTable = new GenericDictionary<>();

        originalProgram = program;
        logFilePath = logPath;

        repo.add(new ProgramState(exeStack, symTable, out, fTable, program));

    }

    public ProgramState getCurrentProgramState(){
        return repo.get(0);

    }


    public void logProgramState() throws LoggingException{
        try {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));

            // separate from previous logs
            logFile.println("---------------");
            logFile.println("LOG START\n");

            // print the Execution Stack text and its contents inorder
            logFile.println("ExeStack:");
            logFile.close();
            logExeStackInorderWrapper();

            // open the writer again and clean up
            logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            logFile.println();

            // print the other structures
            logFile.println("Symbol Table:");
            logFile.print(repo.get(0).getSymbolTable());
            logFile.println();

            logFile.println("Output Stream:");
            logFile.print(repo.get(0).getOutputStream());
            logFile.println();

            logFile.println("File Table:");
            logFile.print(repo.get(0).getFileTable());
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

    public void logExeStackInorderWrapper() throws LoggingException {
        IStack<IStatement> exeStack = repo.get(0).getExecutionStack();

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
