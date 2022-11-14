package model.types;

import model.values.BoolValue;
import model.values.IValue;

public class BoolType implements IType{
    @Override
    public boolean equals(Object another){
        return another instanceof IntType;

    }

    @Override
    public IValue defaultValue() {
        return new BoolValue(false);

    }

    public String toString(){
        return "bool";

    }

}
