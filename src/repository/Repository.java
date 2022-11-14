package repository;
import model.collections.dictionary.GenericDictionary;
import model.collections.dictionary.IDictionary;
import model.collections.list.GenericList;
import model.collections.list.IList;
import model.collections.stack.GenericStack;
import model.collections.stack.IStack;
import model.exceptions.LoggingException;
import model.expressions.ValueExpression;
import model.expressions.VariableExpression;
import model.statements.*;
import model.structures.ProgramState;
import model.types.IntType;
import model.types.StringType;
import model.values.BoolValue;
import model.values.IValue;
import model.values.IntValue;
import model.values.StringValue;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    List<ProgramState> repo = new ArrayList<>();
    public IStatement bigBoy, ifTest, printTest, openRFileTest;
    String logFilePath;

    public Repository(String logPath){
        // create mock program
        bigBoy = new CompoundStatement(
                new VariableDeclarationStatement("v", new IntType()), new CompoundStatement(
                        new VariableAssignmentStatement("v",
                                new ValueExpression(
                                        new IntValue(2))),
                new PrintStatement(new VariableExpression("v"))
                ));

        ifTest =
                new IfStatement(
                        new ValueExpression(new IntValue(0)),
                        new PrintStatement(new ValueExpression(new BoolValue(true))),
                        new PrintStatement(new ValueExpression(new BoolValue(false)))
                );

        printTest =
                new CompoundStatement(
                        new PrintStatement(new ValueExpression(new IntValue(1))),
                        new PrintStatement(new ValueExpression(new IntValue(2)))
                );

        openRFileTest =
                new CompoundStatement(
                        new VariableDeclarationStatement("file", new StringType()),
                        new CompoundStatement(
                                new VariableAssignmentStatement("file",
                                        new ValueExpression(
                                                new StringValue("log.txt"))),

                                new OpenRFile(new VariableExpression("file"))
                        )
                );



        // create Structures
        IStack<IStatement> exeStack = new GenericStack<>();
        IDictionary<String, IValue> symTable = new GenericDictionary<>();
        IList<IValue> out = new GenericList<>();
        IDictionary<StringValue, BufferedReader> fTable = new GenericDictionary<>();

        logFilePath = logPath;

        repo.add(new ProgramState(exeStack, symTable, out, fTable, openRFileTest));

    }

    public ProgramState getCurrentProgramState(){
        return repo.get(0);

    }

    public void changeProgram(int i){

        switch (i) {
            case 1 -> {
                repo.get(0).getExecutionStack().pop();
                repo.get(0).getExecutionStack().push(bigBoy);
                repo.get(0).setOriginalProgram(bigBoy);
            }
            case 2 -> {
                repo.get(0).getExecutionStack().pop();
                repo.get(0).getExecutionStack().push(printTest);
                repo.get(0).setOriginalProgram(printTest);
            }
            case 3 -> {
                repo.get(0).getExecutionStack().pop();
                repo.get(0).getExecutionStack().push(ifTest);
                repo.get(0).setOriginalProgram(ifTest);
            }
            case 4 -> {
                repo.get(0).getExecutionStack().pop();
                repo.get(0).getExecutionStack().push(openRFileTest);
                repo.get(0).setOriginalProgram(openRFileTest);

            }

        }

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
