package model.expressions;

import model.collections.dictionary.IDictionary;
import model.exceptions.ExpressionEvaluationException;
import model.types.ReferenceType;
import model.values.IValue;
import model.values.ReferenceValue;

public class HeapReadExpression implements IExpression{
    IExpression expression;

    public HeapReadExpression(IExpression expr){
        expression = expr;

    }

    public String toString(){
        return "readHeap(" + expression + ")";

    }

    public IValue evaluate(IDictionary<String, IValue> tbl, IDictionary<Integer, IValue> heap) throws ExpressionEvaluationException {
        IValue expressionEvaluationResult = expression.evaluate(tbl, heap);

        if(expressionEvaluationResult.getType() instanceof ReferenceType){
            int referenceAddress =  ((ReferenceValue)expressionEvaluationResult).getAddress();

            if(heap.get(referenceAddress) != null)
                return heap.get(referenceAddress);

            else{
                throw new ExpressionEvaluationException("Specified address in heap not found!");

            }

        }

        else{
            throw new ExpressionEvaluationException("Value provided in readHeap call is not a reference!");

        }

    }


}

