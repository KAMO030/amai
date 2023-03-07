package com.kamo.amai.collection;

import com.kamo.amai.Tuple;
import com.kamo.amai.Tuple2;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayIterator<T> implements Iterator<Tuple2<Integer,T>> {

    private final T[] data;
    private   int p;

    private final int size;

    public ArrayIterator(T... data) {
        this( 0,data);
    }
    public ArrayIterator(int p, T... data) {
        Objects.requireNonNull(data);
        this.data = data;
        this.size = data.length;
    }
    @Override
    public boolean hasNext() {
        return p < size;
    }

    @Override
    public Tuple2<Integer,T> next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return Tuple.of(p,data[p++]);
    }
}
