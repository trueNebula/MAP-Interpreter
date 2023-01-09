package model.expressions;

import model.collections.dictionary.IDictionary;
import model.collections.heap.IHeap;
import model.exceptions.ExpressionEvaluationException;
import model.exceptions.TypeCheckException;
import model.types.IType;
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

    public IValue evaluate(IDictionary<String, IValue> tbl, IHeap heap) throws ExpressionEvaluationException {
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

    @Override
    public IType typeCheck(IDictionary<String, IType> typeEnv) throws TypeCheckException {
        IType type = expression.typeCheck(typeEnv);

        if(!(type instanceof ReferenceType refType))
            throw new TypeCheckException("readHeap argument not of ReferenceType!");

        return refType.getInner();

    }


}

