package model.collections.stack;

public interface IStack<T> {
    T pop();
    void push(T value);
    boolean isEmpty();
    T peek();

}
