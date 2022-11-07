package Repo;
import Model.Collections.Dictionary.GenericDictionary;
import Model.Collections.Dictionary.IDictionary;
import Model.Collections.List.GenericList;
import Model.Collections.List.IList;
import Model.Collections.Stack.GenericStack;
import Model.Collections.Stack.IStack;
import Model.Expressions.ValueExpression;
import Model.Expressions.VariableExpression;
import Model.Statements.*;
import Model.Structures.ProgramState;
import Model.Types.IntType;
import Model.Values.BoolValue;
import Model.Values.IValue;
import Model.Values.IntValue;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    List<ProgramState> repo = new ArrayList<>();

    public IStatement bigBoy, ifTest, printTest;

    public Repository(){
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

        // create Structures
        IStack<IStatement> exeStack = new GenericStack<>();
        IDictionary<String, IValue> symTable = new GenericDictionary<>();
        IList<IValue> out = new GenericList<>();

        repo.add(new ProgramState(exeStack, symTable, out, bigBoy));

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

        }

    }

}
