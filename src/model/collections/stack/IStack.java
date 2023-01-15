package model.collections.stack;

import java.util.List;

public interface IStack<T> {
    T pop();
    void push(T value);
    boolean isEmpty();
    T peek();
    List<T> getReversed();
}
