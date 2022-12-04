package model.types;

import model.values.IValue;
import model.values.ReferenceValue;

public class ReferenceType implements IType{
    IType inner;

    // create a constructor
    public ReferenceType(IType inner){
        this.inner = inner;
    }

    public IType getInner(){
        return inner;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ReferenceType){
            return inner.equals(((ReferenceType) obj).getInner());
        }
        else
            return false;

    }

    public String toString() {
        return inner.toString() + "*";

    }

    @Override
    public IValue defaultValue() {
        return new ReferenceValue(0, inner);

    }
}
