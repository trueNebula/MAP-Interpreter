package model.values;

import model.types.IType;
import model.types.ReferenceType;

public class ReferenceValue implements IValue{
    int address;
    IType locationType;

    public ReferenceValue(int address, IType locationType){
        this.address = address;
        this.locationType = locationType;

    }

    @SuppressWarnings("unused")
    public int getAddress(){
        return address;

    }

    public String toString(){
        return address + ", " + locationType;

    }

    @Override
    public IType getType() {
        return new ReferenceType(locationType);

    }

    @Override
    public boolean equals(IValue val) {
        return false;

    }

    @Override
    protected Object clone() throws CloneNotSupportedException{
        return super.clone();

    }

}
