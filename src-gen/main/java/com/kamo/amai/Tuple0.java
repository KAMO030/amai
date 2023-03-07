package com.kamo.amai;

import com.kamo.amai.collection.TupleIterator;

import java.util.Iterator;
import java.util.Objects;

public class Tuple0 extends Tuple<Tuple0> {
    private Tuple0() {
        synchronized (Tuple0.class) {
            if (Objects.nonNull(INS))
                throw new IllegalStateException();
        }
    }

    public static final Tuple0 INS = new Tuple0();


    @Override
    public Object get(int index) {
        if (index == 0) {
            return INS;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public int arity() {
        return 0;
    }

    @Override
    public Iterator<Tuple2<Integer, Object>> iterator() {
        return new TupleIterator();
    }
}
