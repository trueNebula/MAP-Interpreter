package model.types;

public class BoolType implements IType{
    @Override
    public boolean equals(Object another){
        return another instanceof IntType;

    }

    public String toString(){
        return "bool";

    }

}
