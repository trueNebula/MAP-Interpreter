package model.statements;

import model.collections.dictionary.IDictionary;
import model.collections.stack.GenericStack;
import model.collections.stack.IStack;
import model.exceptions.ExpressionEvaluationException;
import model.exceptions.StatementExecutionException;
import model.structures.ProgramState;
import model.values.IValue;

@SuppressWarnings("unused")
public class ForkStatement implements IStatement{
    IStatement statement;

    public ForkStatement(IStatement stmt){
        statement = stmt;

    }

    @Override
    public String toString() {
        return "fork(" + statement + "); ";

    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException {
        IStack<IStatement> childExeStack = new GenericStack<>();
        IDictionary<String, IValue> childSymTable = state.getSymbolTable().clone();

        childExeStack.push(statement);

        ProgramState childThread = new ProgramState(childExeStack, childSymTable, state.getOutputStream(), state.getFileTable(), state.getHeapTable());
        childThread.setID();

        return childThread;

    }

}
