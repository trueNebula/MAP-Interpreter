package model.collections.heap;

import model.values.IValue;

import java.util.HashMap;

public class GenericHeap implements IHeap{
    HashMap<Integer, IValue> elems;
    int nextFree;

    public GenericHeap(){
        elems = new HashMap<>();
        nextFree = 1;

    }

    @Override
    public void put(int key, IValue value) {
        elems.put(key, value);
        nextFree = findFree();

    }

    @Override
    public IValue get(int key) {
        return elems.get(key);

    }

    @Override
    public boolean isEmpty() {
        return size() == 0;

    }

    @Override
    public int size() {
        return elems.size();

    }

    @Override
    public void setElems(HashMap<Integer, IValue> newContent) {
        elems = newContent;

    }

    @Override
    public HashMap<Integer, IValue> getElems() {
        return elems;

    }

    public int findFree(){
        while(elems.get(nextFree) != null)
            nextFree++;

        return nextFree;

    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for(Integer i: elems.keySet()){
            result.append(i);
            result.append(" --> ");
            result.append(elems.get(i));
            result.append("\n");

        }

        return result.toString();

    }

}
