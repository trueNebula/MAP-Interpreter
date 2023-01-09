package model.expressions;

import model.collections.dictionary.IDictionary;
import model.collections.heap.IHeap;
import model.exceptions.ExpressionEvaluationException;
import model.exceptions.TypeCheckException;
import model.types.IType;
import model.types.IntType;
import model.values.BoolValue;
import model.values.IValue;
import model.values.IntValue;

@SuppressWarnings("unused")
public class RelationalExpression implements IExpression{
    IExpression exp1;
    IExpression exp2;
    String operator;
    // <
    // <=
    // ==
    // !=
    // >=
    // >

    public RelationalExpression(IExpression e1, String o, IExpression e2){
        exp1 = e1;
        exp2 = e2;
        operator = o;

    }

    @Override
    public String toString() {
        return exp1.toString() + " " + operator + " " + exp2;

    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> tbl, IHeap heap) throws ExpressionEvaluationException {
        IValue v1, v2;
        v1 = exp1.evaluate(tbl, heap);

        if (v1.getType().equals(new IntType())){
            v2 = exp2.evaluate(tbl, heap);

            if(v2.getType().equals(new IntType())){
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;

                int i1Value = i1.getValue();
                int i2Value = i2.getValue();


                switch (operator) {
                    case "<" -> {
                        return new BoolValue(i1Value < i2Value);
                    }
                    case "<=" -> {
                        return new BoolValue(i1Value <= i2Value);
                    }
                    case "==" -> {
                        return new BoolValue(i1Value == i2Value);
                    }
                    case "!=" -> {
                        return new BoolValue(i1Value != i2Value);
                    }
                    case ">=" -> {
                        return new BoolValue(i1Value >= i2Value);
                    }
                    case ">" -> {
                        return new BoolValue(i1Value > i2Value);
                    }
                }

            }

            else
                throw new ExpressionEvaluationException("Second Operand is not an Integer!");

        }

        else
            throw new ExpressionEvaluationException("First Operand is not an Integer!");

        return new BoolValue(false);

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

}
