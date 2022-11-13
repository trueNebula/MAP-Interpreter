package Model.Values;

import Model.Types.BoolType;
import Model.Types.IType;

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

}
