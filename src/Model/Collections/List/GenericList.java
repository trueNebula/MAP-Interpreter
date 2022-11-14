package Model.Collections.List;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class GenericList<T> implements IList<T>, Iterable<T>{
    ArrayList<T> elems;

    public GenericList(){
        elems = new ArrayList<>();

    }

    @Override
    public T get(int index){
        return elems.get(index);

    }

    @Override
    public void add(int index, T value) {
        elems.add(index, value);

    }

    @Override
    public void add(T value) {
        elems.add(value);

    }

    @Override
    public void set(int index, T value) {
        elems.set(index, value);

    }

    @Override
    public void remove(int index) {
        elems.remove(index);

    }

    @Override
    public void remove(T value) {
        elems.remove(value);

    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator<>(this);

    }

    @Override
    public int size(){
        return elems.size();

    }

    @Override
    public void clear(){
        elems.clear();

    }

    @Override
    public boolean isEmpty(){
        return elems.isEmpty();

    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for(T i: elems){
            result.append(i);
            result.append("\n");

        }

        return result.toString();

    }

}

class ListIterator<T> implements Iterator<T>{
    int currentIndex;
    GenericList<T> listReference;

    public ListIterator(GenericList<T> list){
        currentIndex = 0;
        listReference = list;

    }

    @Override
    public boolean hasNext() {
        return currentIndex + 1 <= listReference.size();
    }

    @Override
    public T next() throws NoSuchElementException {
        if(hasNext())
            currentIndex++;

        else throw new NoSuchElementException();

        return listReference.get(currentIndex - 1);

    }

}