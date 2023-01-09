package model.expressions;

import model.collections.dictionary.IDictionary;
import model.collections.heap.IHeap;
import model.exceptions.ExpressionEvaluationException;
import model.exceptions.TypeCheckException;
import model.types.BoolType;
import model.types.IType;
import model.values.IValue;
import model.values.BoolValue;

@SuppressWarnings("unused")
public class LogicalExpression implements IExpression{
    IExpression exp1;
    IExpression exp2;
    char operator;
    // & - and
    // | - or

    @Override
    public IValue evaluate(IDictionary<String, IValue> tbl, IHeap heap) throws ExpressionEvaluationException {
        IValue v1, v2;
        v1 = exp1.evaluate(tbl, heap);

        if (v1.getType().equals(new BoolType())){
            v2 = exp2.evaluate(tbl, heap);

            if(v2.getType().equals(new BoolType())){
                BoolValue i1 = (BoolValue)v1;
                BoolValue i2 = (BoolValue)v2;

                boolean b1, b2;
                b1 = i1.getValue();
                b2 = i2.getValue();

                switch (operator) {
                    case '&' -> {
                        return new BoolValue(b1 && b2);
                    }
                    case '|' -> {
                        return new BoolValue(b1 || b2);
                    }
                }

            }

            else
                throw new ExpressionEvaluationException("Second Operand is not a Boolean!");

        }

        else
            throw new ExpressionEvaluationException("First Operand is not a Boolean!");

        return new BoolValue(false);

    }

    @Override
    public IType typeCheck(IDictionary<String, IType> typeEnv) throws TypeCheckException {
        IType type1, type2;
        type1 = exp1.typeCheck(typeEnv);
        type2 = exp2.typeCheck(typeEnv);

        if(!type1.equals(new BoolType()))
            throw new TypeCheckException("First operand is not of boolean type!");

        if(!type2.equals(new BoolType()))
            throw new TypeCheckException("Second operand is not of boolean type!");

        return new BoolType();

    }

    @Override
    public String toString() {
        return exp1.toString() + " " + operator + operator + "" + exp2;
    }


}
