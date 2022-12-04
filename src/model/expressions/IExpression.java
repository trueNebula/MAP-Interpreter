package model.expressions;

import model.collections.dictionary.IDictionary;
import model.exceptions.ExpressionEvaluationException;
import model.values.IValue;

public interface IExpression {
    IValue evaluate(IDictionary<String, IValue> tbl, IDictionary<Integer, IValue> heap) throws ExpressionEvaluationException;


}
