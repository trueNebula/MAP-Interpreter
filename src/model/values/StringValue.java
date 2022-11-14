package model.values;

import model.types.IType;
import model.types.StringType;

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

}
