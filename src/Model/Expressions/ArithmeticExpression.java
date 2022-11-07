package Model.Expressions;

import Model.Collections.Dictionary.IDictionary;
import Model.Exceptions.ExpressionEvaluationException;
import Model.Types.IntType;
import Model.Values.IValue;
import Model.Values.IntValue;

public class ArithmeticExpression implements IExpression{
    IExpression exp1;
    IExpression exp2;
    char operator;
    // + - addition
    // - - subtraction
    // * - multiplication
    // / - division

    @Override
    public IValue evaluate(IDictionary<String, IValue> tbl) throws ExpressionEvaluationException {
        IValue v1, v2;
        v1 = exp1.evaluate(tbl);

        if (v1.getType().equals(new IntType())){
            v2 = exp2.evaluate(tbl);

            if(v2.getType().equals(new IntType())){
                IntValue i1 = (IntValue)v1;
                IntValue i2 = (IntValue)v2;

                int n1, n2;
                n1 = i1.getValue();
                n2 = i2.getValue();

                switch(operator){
                    case '+':
                        return new IntValue(n1 + n2);

                    case '-':
                        return new IntValue(n1 - n2);

                    case '*':
                        return new IntValue(n1 * n2);

                    case '/':
                        if(n2 == 0)
                            throw new ExpressionEvaluationException("Division by zero");

                        return new IntValue(n1 / n2);

                }

            }

            else
                throw new ExpressionEvaluationException("Second Operand is not an Integer");

        }

        else
            throw new ExpressionEvaluationException("First Operand is not an Integer");

        return new IntValue(0);

    }


}
