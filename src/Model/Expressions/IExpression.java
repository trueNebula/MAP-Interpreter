package Model.Expressions;

import Model.Collections.Dictionary.IDictionary;
import Model.Exceptions.ExpressionEvaluationException;
import Model.Values.IValue;

public interface IExpression {
    IValue evaluate(IDictionary<String, IValue> tbl) throws ExpressionEvaluationException;



}
