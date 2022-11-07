package Model.Statements;

import Model.Collections.Dictionary.IDictionary;
import Model.Collections.List.IList;
import Model.Collections.Stack.IStack;
import Model.Exceptions.ExpressionEvaluationException;
import Model.Exceptions.StatementExecutionException;
import Model.Expressions.IExpression;
import Model.Structures.ProgramState;
import Model.Values.IValue;

public class PrintStatement implements IStatement{
    IExpression expr;

    public PrintStatement(IExpression e){
        expr = e;

    }

    public String toString(){
        return "print(" + expr.toString() + "); ";

    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException {
        IDictionary<String, IValue> symTable = state.getSymbolTable();
        IList<IValue> out = state.getOutputStream();

        out.add(expr.evaluate(symTable));

        return state;

    }

}
