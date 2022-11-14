package repository;
import model.collections.dictionary.GenericDictionary;
import model.collections.dictionary.IDictionary;
import model.collections.list.GenericList;
import model.collections.list.IList;
import model.collections.stack.GenericStack;
import model.collections.stack.IStack;
import model.expressions.ValueExpression;
import model.expressions.VariableExpression;
import model.statements.*;
import model.structures.ProgramState;
import model.types.IntType;
import model.values.BoolValue;
import model.values.IValue;
import model.values.IntValue;

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
