package Repo;
import Model.Collections.Dictionary.GenericDictionary;
import Model.Collections.Dictionary.IDictionary;
import Model.Collections.List.GenericList;
import Model.Collections.List.IList;
import Model.Collections.Stack.GenericStack;
import Model.Collections.Stack.IStack;
import Model.Statements.*;
import Model.Structures.ProgramState;
import Model.Values.IValue;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    List<ProgramState> repo = new ArrayList<>();

    public Repository(){
        IStack<IStatement> exeStack = new GenericStack<>();
        IDictionary<String, IValue> symTable = new GenericDictionary<>();
        IList<IValue> out = new GenericList<>();
        IStatement original = new NoStatement();

        repo.add(new ProgramState(exeStack, symTable, out, original));

    }

    public ProgramState getCurrentProgramState(){
        return repo.get(0);

    }

}
