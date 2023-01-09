package model.expressions;

import model.collections.dictionary.IDictionary;
import model.collections.heap.IHeap;
import model.exceptions.ExpressionEvaluationException;
import model.exceptions.TypeCheckException;
import model.types.IType;
import model.values.IValue;

public interface IExpression {
    IValue evaluate(IDictionary<String, IValue> tbl, IHeap heap) throws ExpressionEvaluationException;
    IType typeCheck(IDictionary<String, IType> typeEnv) throws TypeCheckException;

}
