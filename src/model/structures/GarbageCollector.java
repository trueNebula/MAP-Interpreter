package model.structures;

import model.values.IValue;
import model.values.ReferenceValue;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GarbageCollector {
    public static HashMap<Integer, IValue> collect(List<Integer> symbolTableAddresses, List<Integer> heapAddresses, HashMap<Integer, IValue> heapTable){
        return heapTable.entrySet().stream()
                .filter(e -> (symbolTableAddresses.contains(e.getKey()) || heapAddresses.contains(e.getKey())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, HashMap::new));

    }

    public static void threadCollect(List<ProgramState> programStateList){
        List<Integer> symTableAddresses = Objects.requireNonNull(programStateList.stream()
                        .map(p -> getAddressesFromSymbolTable(p.getSymbolTable().getElems().values()))
                        .map(Collection::stream)
                        .reduce(Stream::concat).orElse(null))
                        .toList();

        programStateList.forEach(p -> p.getHeapTable().setElems(
                collect(symTableAddresses,
                        getAddrFromHeap(p.getHeapTable().getElems().values()),
                        p.getHeapTable().getElems())
        ));

    }

    public static List<Integer> getAddressesFromSymbolTable(Collection<IValue> symTableValues){
        return symTableValues.stream()
                .filter(v -> v instanceof ReferenceValue)
                .map(v-> {ReferenceValue v1 = (ReferenceValue)v; return v1.getAddress();})
                .collect(Collectors.toList());

    }

    public static List<Integer> getAddrFromHeap(Collection<IValue> heapValues) {
        return heapValues.stream()
                .filter(v -> v instanceof ReferenceValue)
                .map(v -> {ReferenceValue v1 = (ReferenceValue) v; return v1.getAddress();})
                .collect(Collectors.toList());
    }

}
