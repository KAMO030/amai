package com.kamo.amai;

import com.kamo.amai.collection.TupleIterator;

import java.util.Iterator;
import java.util.Objects;

public class Tuple1<T1> extends Tuple<Tuple1<T1>> {

    public final T1 _1;

    public Tuple1(T1 t1){
        _1 = t1;
    }

    public <U> Tuple1<U> update1(U val) {
        return Tuple.of(val);
    }

    @Override
    public Object get(int index) {
        if (index == 0) {
            return Tuple0.INS;
        }
        switch (index) {
            case 1: return _1;
            default:throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple1<?> tuple1 = (Tuple1<?>) o;
        return Objects.equals(_1, tuple1._1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_1);
    }
    @Override
    public Iterator<Tuple2<Integer,Object>> iterator() {
        return new TupleIterator(_1);
    }
    @Override
    public int arity() {
        return 1;
    }
}
