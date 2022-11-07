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
import Model.Values.IValue;
import Model.Values.IntValue;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    List<ProgramState> repo = new ArrayList<>();

    public Repository(){
        // create mock program
        IStatement original = new CompoundStatement(
                new VariableDeclarationStatement("v", new IntType()), new CompoundStatement(
                        new VariableAssignmentStatement("v",
                                new ValueExpression(
                                        new IntValue(2))),
                new PrintStatement(new VariableExpression("v"))
                ));


        // create Structures
        IStack<IStatement> exeStack = new GenericStack<>();
        IDictionary<String, IValue> symTable = new GenericDictionary<>();
        IList<IValue> out = new GenericList<>();

        repo.add(new ProgramState(exeStack, symTable, out, original));

    }

    public ProgramState getCurrentProgramState(){
        return repo.get(0);

    }

}
