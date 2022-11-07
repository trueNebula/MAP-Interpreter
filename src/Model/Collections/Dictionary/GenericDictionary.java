package Model.Collections.Dictionary;

import java.util.Dictionary;
import java.util.Map;

public class GenericDictionary<T, K> implements IDictionary<T, K> {
    Map<T, K> elems;

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
}
