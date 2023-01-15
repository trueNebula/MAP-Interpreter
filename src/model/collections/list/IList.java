package model.collections.list;

import java.util.ArrayList;

public interface IList<T> {
    T get(int index);
    void add(int index, T value);
    void add(T value);
    @SuppressWarnings("unused")
    void set(int index, T value);
    @SuppressWarnings("unused")
    void remove(int index);
    @SuppressWarnings("unused")
    void remove(T value);
    int size();
    void clear();
    boolean isEmpty();
    ArrayList<T> getElems();

}
