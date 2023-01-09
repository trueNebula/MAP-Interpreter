package model.expressions;

import model.collections.dictionary.IDictionary;
import model.collections.heap.IHeap;
import model.exceptions.ExpressionEvaluationException;
import model.exceptions.TypeCheckException;
import model.types.IType;
import model.types.IntType;
import model.values.IValue;
import model.values.IntValue;

@SuppressWarnings("unused")
public class ArithmeticExpression implements IExpression{
    IExpression exp1;
    IExpression exp2;
    char operator;
    // + - addition
    // - - subtraction
    // * - multiplication
    // / - division

    public ArithmeticExpression(IExpression expression1, char op, IExpression expression2){
        exp1 = expression1;
        exp2 = expression2;
        operator = op;

    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> tbl, IHeap heap) throws ExpressionEvaluationException {
        IValue v1, v2;
        v1 = exp1.evaluate(tbl, heap);

        if (v1.getType().equals(new IntType())){
            v2 = exp2.evaluate(tbl, heap);

            if(v2.getType().equals(new IntType())){
                IntValue i1 = (IntValue)v1;
                IntValue i2 = (IntValue)v2;

                int n1, n2;
                n1 = i1.getValue();
                n2 = i2.getValue();

                switch (operator) {
                    case '+' -> {
                        return new IntValue(n1 + n2);
                    }
                    case '-' -> {
                        return new IntValue(n1 - n2);
                    }
                    case '*' -> {
                        return new IntValue(n1 * n2);
                    }
                    case '/' -> {
                        if (n2 == 0)
                            throw new ExpressionEvaluationException("Division by zero");
                        return new IntValue(n1 / n2);
                    }
                }

            }

            else
                throw new ExpressionEvaluationException("Second Operand is not an Integer");

        }

        else
            throw new ExpressionEvaluationException("First Operand is not an Integer");

        return new IntValue(0);

    }

    @Override
    public IType typeCheck(IDictionary<String, IType> typeEnv) throws TypeCheckException {
        IType type1, type2;
        type1 = exp1.typeCheck(typeEnv);
        type2 = exp2.typeCheck(typeEnv);

        if(!type1.equals(new IntType()))
            throw new TypeCheckException("First operand is not of integer type!");

        if(!type2.equals(new IntType()))
            throw new TypeCheckException("Second operand is not of integer type!");

        return new IntType();

    }

    @Override
    public String toString() {
        return exp1.toString() + " " + operator + " " + exp2;
    }

}
