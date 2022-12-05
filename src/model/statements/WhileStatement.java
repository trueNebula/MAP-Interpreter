package model.statements;

import model.collections.dictionary.IDictionary;
import model.collections.stack.IStack;
import model.exceptions.ExpressionEvaluationException;
import model.exceptions.StatementExecutionException;
import model.expressions.IExpression;
import model.structures.ProgramState;
import model.types.BoolType;
import model.values.BoolValue;
import model.values.IValue;

public class WhileStatement implements IStatement{
    IExpression expression;
    IStatement statement;

    public WhileStatement(IExpression expr, IStatement stmt){
        expression = expr;
        statement = stmt;

    }

    public String toString(){
        return "while(" + expression + "){ " + statement + " };";

    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException {
        IStack<IStatement> exeStack = state.getExecutionStack();
        IDictionary<String, IValue> symTable = state.getSymbolTable();
        IDictionary<Integer, IValue> heapTable = state.getHeapTable();

        IValue expressionEvaluationResult = expression.evaluate(symTable, heapTable);

        // check if expression evaluates to boolean type
        if(expressionEvaluationResult.getType() instanceof BoolType){

            // expression evaluates to true, enter while loop
            if(((BoolValue)expressionEvaluationResult).getValue()){
                // push the statement inside the while statement
                // repeat the while statement
                exeStack.push(new WhileStatement(expression, statement));
                exeStack.push(statement);

            }

            // expression evaluates to false, ignore statement in while
            // no need to push anything to the exe stack

        }

        else {
            throw new StatementExecutionException("Expression is not a boolean!");

        }

        return state;

    }

}
