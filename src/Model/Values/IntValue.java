package Model.Values;

import Model.Types.IType;
import Model.Types.IntType;

public class IntValue implements IValue{
    int value;

    public IntValue(int val){
        value = val;

    }

    public int getValue(){
        return value;

    }

    public String toString(){
        return Integer.toString(value);

    }

    @Override
    public IType getType() {
        return new IntType();

    }

}
