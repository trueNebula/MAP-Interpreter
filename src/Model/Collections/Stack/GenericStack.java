package Model.Collections.Stack;
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

}
