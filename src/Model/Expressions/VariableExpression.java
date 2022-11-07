package Model.Expressions;

import Model.Collections.Dictionary.IDictionary;
import Model.Exceptions.ExpressionEvaluationException;
import Model.Values.IValue;

public class VariableExpression implements IExpression{
    String id;



    @Override
    public IValue evaluate(IDictionary<String, IValue> tbl) throws ExpressionEvaluationException {
        return tbl.get(id);

    }

}
