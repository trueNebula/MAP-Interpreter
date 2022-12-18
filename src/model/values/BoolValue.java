package model.values;

import model.types.BoolType;
import model.types.IType;

public class BoolValue implements IValue{
    boolean value;

    public BoolValue(boolean val){
        value = val;

    }

    public boolean getValue(){
        return value;

    }

    public String toString(){
        return ""+value;

    }

    @Override
    public IType getType() {
        return new BoolType();

    }

    @Override
    public boolean equals(IValue val){
        if(val == this)
            return true;

        if(val instanceof BoolValue)
            return ((BoolValue) val).getValue() == value;

        return false;

    }

    @Override
    protected Object clone() throws CloneNotSupportedException{
        return super.clone();

    }

}
