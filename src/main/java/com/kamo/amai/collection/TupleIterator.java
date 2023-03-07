package com.kamo.amai.collection;

import com.kamo.amai.Tuple;
import com.kamo.amai.Tuple2;

import java.util.NoSuchElementException;
import java.util.Objects;

public class TupleIterator extends ArrayIterator<Object> {
    private final Object[] data;
    private  int p = 1;

    private final int size;

    public TupleIterator(Object... data) {
        Objects.requireNonNull(data);
        this.data = data;
        this.size = data.length;
    }

    @Override
    public boolean hasNext() {
        return p <= size;
    }

    @Override
    public Tuple2<Integer,Object> next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int index = p++;
        return Tuple.of(index,data[index-1]);
    }
}
