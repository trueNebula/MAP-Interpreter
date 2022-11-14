package model.collections.dictionary;

public interface IDictionary<T, K> {
    void put(T key, K value);
    K get(T key);
    boolean isEmpty();
    void remove(T key);
    int size();

}
