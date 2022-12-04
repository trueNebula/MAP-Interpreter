package model.expressions;

import model.collections.dictionary.IDictionary;
import model.exceptions.ExpressionEvaluationException;
import model.types.BoolType;
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
    public IValue evaluate(IDictionary<String, IValue> tbl, IDictionary<Integer, IValue> heap) throws ExpressionEvaluationException {
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

                switch(operator){
                    case '&':
                        return new BoolValue(b1 && b2);

                    case '|':
                        return new BoolValue(b1 || b2);

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
    public String toString() {
        return exp1.toString() + " " + operator + operator + "" + exp2;
    }


}
