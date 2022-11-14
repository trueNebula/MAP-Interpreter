package model.types;

public class StringType implements IType{
    @Override
    public boolean equals(Object another){
        return another instanceof IntType;

    }

    public String toString(){
        return "string";

    }

}
