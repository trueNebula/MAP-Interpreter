package model.collections.heap;

import model.values.IValue;

import java.util.HashMap;

public interface IHeap {
    void put(int key, IValue value);
    IValue get(int key);
    boolean isEmpty();
    int size();
    void setElems(HashMap<Integer, IValue> newContent);
    HashMap<Integer, IValue> getElems();
    int findFree();

}
