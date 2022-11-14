package model.statements;

import model.collections.dictionary.IDictionary;
import model.collections.list.IList;
import model.exceptions.ExpressionEvaluationException;
import model.expressions.IExpression;
import model.structures.ProgramState;
import model.values.IValue;

@SuppressWarnings("unused")
public class PrintStatement implements IStatement{
    IExpression expr;

    public PrintStatement(IExpression e){
        expr = e;

    }

    public String toString(){
        return "print(" + expr.toString() + "); ";

    }

    @Override
    public ProgramState execute(ProgramState state) throws ExpressionEvaluationException {
        IDictionary<String, IValue> symTable = state.getSymbolTable();
        IList<IValue> out = state.getOutputStream();

        out.add(expr.evaluate(symTable));

        return state;

    }

}
