package Model.Statements;

import Model.Collections.Dictionary.IDictionary;
import Model.Collections.Stack.IStack;
import Model.Exceptions.ExpressionEvaluationException;
import Model.Expressions.IExpression;
import Model.Structures.ProgramState;
import Model.Values.IValue;

@SuppressWarnings("unused")
public class IfStatement implements IStatement{
    IExpression expr;
    IStatement thenStatement;
    IStatement elseStatement;

    public IfStatement(IExpression e, IStatement thenS, IStatement elseS){
        expr = e;
        thenStatement = thenS;
        elseStatement = elseS;

    }

    public String toString(){
        return "if(" + expr.toString() + ") then (" + thenStatement.toString() + ") else (" + elseStatement.toString() + "))";

    }

    @Override
    public ProgramState execute(ProgramState state) throws ExpressionEvaluationException {
        IStack<IStatement> exeStack = state.getExecutionStack();
        IDictionary<String, IValue> symTable = state.getSymbolTable();

        IValue exprEvalResult = expr.evaluate(symTable);
        boolean exprEvalResultBool;

        if(exprEvalResult.getType().toString().equals("bool"))
            exprEvalResultBool = exprEvalResult.toString().equals("true");

        else
            exprEvalResultBool = (Integer.parseInt(exprEvalResult.toString()) != 0);

        if(exprEvalResultBool)
            // expr = true, move to THEN branch
            exeStack.push(thenStatement);


        else
            // expr = false, move to ELSE branch
            exeStack.push(elseStatement);

        return state;

    }

}
