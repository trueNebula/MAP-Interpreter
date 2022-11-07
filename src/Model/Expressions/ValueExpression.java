package Model.Expressions;

import Model.Collections.Dictionary.IDictionary;
import Model.Values.IValue;

@SuppressWarnings("unused")
public class ValueExpression implements IExpression{
    IValue e;

    public ValueExpression(IValue val){
        e = val;

    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> tbl) {
        return e;

    }

}
