package com.kamo.amai;

import com.kamo.amai.collection.TupleIterator;

import java.util.Iterator;
import java.util.Objects;

public class Tuple2<T1,T2>extends Tuple<Tuple2<T1,T2>> {
    public final T1 _1;
    public final T2 _2;


    public Tuple2(T1 t1, T2 t2){
        this._1 = t1;
        this._2 = t2;

    }


    public <U> Tuple2<U, T2> update1(U val) {
        return Tuple.of(val,_2);
    }
    public <U> Tuple2<T1, U> update2(U val) {
        return Tuple.of(_1,val);
    }

    @Override
    public Object get(int index) {
        if (index == 0) {
            return Tuple0.INS;
        }
        switch (index) {
            case 1: return _1;
            case 2: return _2;
            default:throw new IndexOutOfBoundsException();
        }
    }
    @Override
    public int arity() {
        return 2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple2 tuple2 = (Tuple2) o;
        return Objects.equals(_1, tuple2._1)
                && Objects.equals(_2, tuple2._2);
    }
    @Override
    public Iterator<Tuple2<Integer,Object>> iterator() {
        return new TupleIterator(_1, _2);
    }
    @Override
    public int hashCode() {
        return Objects.hash(_1, _2);
    }
}
