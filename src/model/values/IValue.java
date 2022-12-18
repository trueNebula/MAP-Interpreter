package model.values;

import model.types.IType;

public interface IValue extends Cloneable {
    IType getType();
    boolean equals(IValue val);

}
