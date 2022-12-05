package model.expressions;

import model.collections.dictionary.IDictionary;
import model.collections.heap.IHeap;
import model.exceptions.ExpressionEvaluationException;
import model.values.IValue;

public interface IExpression {
    IValue evaluate(IDictionary<String, IValue> tbl, IHeap heap) throws ExpressionEvaluationException;


}
