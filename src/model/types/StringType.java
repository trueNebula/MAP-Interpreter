package model.types;

import model.values.IValue;
import model.values.StringValue;

public class StringType implements IType{
    @Override
    public boolean equals(Object another){
        return another instanceof IntType;

    }

    @Override
    public IValue defaultValue() {
        return new StringValue("");

    }

    public String toString(){
        return "string";

    }

}
