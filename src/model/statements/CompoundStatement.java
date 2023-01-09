package model.statements;

import model.collections.dictionary.IDictionary;
import model.collections.stack.IStack;
import model.exceptions.TypeCheckException;
import model.structures.ProgramState;
import model.types.IType;

@SuppressWarnings("unused")
public class CompoundStatement implements IStatement{
    IStatement first;
    IStatement second;

    public CompoundStatement(IStatement f, IStatement s){
        first = f;
        second = s;

    }

    public String toString(){
        return "(" + first.toString() + " " + second.toString() + ")";

    }

    @Override
    public ProgramState execute(ProgramState state) {
        IStack<IStatement> exeStack = state.getExecutionStack();

        exeStack.push(second);
        exeStack.push(first);

        return null;

    }

    @Override
    public IDictionary<String, IType> typeCheck(IDictionary<String, IType> typeEnv) throws TypeCheckException {
        return second.typeCheck(first.typeCheck(typeEnv));

    }

    public IStatement getFirst() {
        return first;

    }

    public IStatement getSecond() {
        return second;

    }

}
