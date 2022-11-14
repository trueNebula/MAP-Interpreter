package model.collections.stack;
import java.util.Stack;

public class GenericStack<T> implements IStack<T>{
    Stack<T> elems;

    public GenericStack(){
        elems = new Stack<>();

    }

    @Override
    public T pop(){
        return elems.pop();

    }

    @Override
    public void push(T value){
        elems.push(value);

    }

    @Override
    public boolean isEmpty(){
        return elems.isEmpty();

    }

    @Override
    public T peek(){
        return elems.peek();

    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (T i : elems) {
            result.append(i);
            result.append("\n");

        }

        return result.toString();

    }

}
