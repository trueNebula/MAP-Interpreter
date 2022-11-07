package Model.Collections.List;

public interface IList<T> {
    T get(int index);
    void add(int index, T value);
    void add(T value);
    void set(int index, T value);
    void remove(int index);
    void remove(T value);
    int size();
    void clear();
    boolean isEmpty();

}
