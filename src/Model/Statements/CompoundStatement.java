package Model.Statements;

import Model.Collections.Stack.IStack;
import Model.Exceptions.StatementExecutionException;
import Model.Structures.ProgramState;

public class CompoundStatement implements IStatement{
    IStatement first;
    IStatement second;

    public CompoundStatement(IStatement f, IStatement s){
        first = f;
        second = s;

    }

    public String toString(){
        return "(" + first.toString() + "; " + second.toString() + ")";

    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException {
        IStack<IStatement> exeStack = state.getExecutionStack();

        exeStack.push(second);
        exeStack.push(first);

        return state;

    }

}
