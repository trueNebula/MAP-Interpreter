package model.types;

public class IntType implements IType{
    @Override
    public boolean equals(Object another){
        return another instanceof IntType;

    }

    public String toString(){
        return "int";

    }

}
