package model.expressions;

import model.collections.dictionary.IDictionary;
import model.collections.heap.IHeap;
import model.types.IType;
import model.values.IValue;

@SuppressWarnings("unused")
public class VariableExpression implements IExpression{
    String id;

    public VariableExpression(String name){
        id = name;

    }

    @Override
    public String toString() {
        return id;

    }

    @Override
    public IValue evaluate(IDictionary<String, IValue> tbl, IHeap heap) {
        return tbl.get(id);

    }

    @Override
    public IType typeCheck(IDictionary<String, IType> typeEnv){
        return typeEnv.get(id);

    }

}
