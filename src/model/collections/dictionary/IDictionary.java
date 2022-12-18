package model.collections.dictionary;

import java.util.HashMap;

public interface IDictionary<T, K> extends Cloneable{
    void put(T key, K value);
    K get(T key);
    boolean isEmpty();
    void remove(T key);
    int size();
    HashMap<T, K> getElems();
    void setElems(HashMap<T, K> elems);
    IDictionary<T, K> clone();


}
