package model.values;

import model.types.IType;
import model.types.StringType;

import java.util.Objects;

public class StringValue implements IValue{
    String value;

    public StringValue(String val){
        value = val;

    }

    public String getValue(){
        return value;

    }

    public String toString(){
        return value;

    }

    @Override
    public IType getType() {
        return new StringType();

    }

    @Override
    public boolean equals(IValue val){
        if(val == this)
            return true;

        if(val instanceof StringValue)
            return Objects.equals(((StringValue) val).getValue(), value);

        return false;

    }

}
