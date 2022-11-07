package Model.Expressions;

import Model.Collections.Dictionary.IDictionary;
import Model.Exceptions.ExpressionEvaluationException;
import Model.Values.IValue;

public class ValueExpression implements IExpression{
    IValue e;

    @Override
    public IValue evaluate(IDictionary<String, IValue> tbl) throws ExpressionEvaluationException {
        return e;

    }

}
