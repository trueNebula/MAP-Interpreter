package model.statements;

import model.collections.dictionary.IDictionary;
import model.exceptions.TypeCheckException;
import model.structures.ProgramState;
import model.types.IType;

@SuppressWarnings("unused")
public class NoStatement implements IStatement{

    public String toString(){
        return "nop; ";

    }

    @Override
    public ProgramState execute(ProgramState state) {
        return null;

    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnv) throws TypeCheckException {
        return typeEnv;
    }

}
