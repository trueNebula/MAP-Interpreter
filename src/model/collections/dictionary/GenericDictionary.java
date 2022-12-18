package model.collections.dictionary;

import java.util.HashMap;

public class GenericDictionary<T, K> implements IDictionary<T, K>, Cloneable{
    HashMap<T, K> elems;

    public GenericDictionary(){
        elems = new HashMap<>();

    }

    @Override
    public void put(T key, K value) {
        elems.put(key, value);

    }

    @Override
    public K get(T key) {
        return elems.get(key);

    }

    @Override
    public boolean isEmpty() {
        return size() == 0;

    }

    @Override
    public void remove(T key) {
        elems.remove(key);
    }

    @Override
    public int size() {
        return elems.size();

    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for(T i: elems.keySet()){
            result.append(i);
            result.append(" --> ");
            result.append(elems.get(i));
            result.append("\n");

        }

        return result.toString();

    }

    @Override
    public HashMap<T, K> getElems(){
        return elems;

    }

    public void setElems(HashMap<T, K> newElems){
        elems = newElems;

    }

    @Override
    @SuppressWarnings("unchecked")
    public IDictionary<T, K> clone(){
        final IDictionary<T, K> clonedDictionary;
        try {

            clonedDictionary = (GenericDictionary<T, K>) super.clone();

        }

        catch(CloneNotSupportedException e){
            throw new RuntimeException(e);

        }

        clonedDictionary.setElems((HashMap<T, K>) elems.clone());

        return clonedDictionary;

    }


}
