package Model.Statements;

import Model.Structures.ProgramState;

@SuppressWarnings("unused")
public class NoStatement implements IStatement{

    public String toString(){
        return "nop; ";

    }

    @Override
    public ProgramState execute(ProgramState state) {
        return state;

    }

}
