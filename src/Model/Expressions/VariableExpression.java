package Model.Expressions;

import Model.Collections.Dictionary.IDictionary;
import Model.Values.IValue;

@SuppressWarnings("unused")
public class VariableExpression implements IExpression{
    String id;

    public VariableExpression(String name){
        id = name;

    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> tbl) {
        return tbl.get(id);

    }

    @Override
    public String toString() {
        return id;
    }
}
