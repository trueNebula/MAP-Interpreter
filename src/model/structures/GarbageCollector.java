package model.structures;

import model.values.IValue;
import model.values.ReferenceValue;

import java.util.*;
import java.util.stream.Collectors;

public class GarbageCollector {
    public static HashMap<Integer, IValue> collect(List<Integer> symbolTableAddresses, HashMap<Integer, IValue> heapTable){
        return heapTable.entrySet().stream()
                .filter(entry -> symbolTableAddresses.contains(entry.getKey()) ||
                        heapTable.entrySet().stream()
                                .anyMatch(entry1 -> entry1.getValue() instanceof ReferenceValue && ((ReferenceValue) entry1.getValue()).getAddress() == entry.getKey())
                )
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, HashMap::new));

    }

    public static List<Integer> getAddressesFromSymbolTable(Collection<IValue> symTableValues){
        return symTableValues.stream()
                .filter(v -> v instanceof ReferenceValue)
                .map(v-> {ReferenceValue v1 = (ReferenceValue)v; return v1.getAddress();})
                .collect(Collectors.toList());

    }

}
