package com.kamo.amai;

import com.kamo.amai.collection.ArrayIterator;

import java.util.Iterator;
import java.util.Objects;

public class Tuple3<T1,T2,T3>extends Tuple<Tuple3<T1,T2,T3>>  {
    public final T1 _1;
    public final T2 _2;
    public final T3 _3;


    public Tuple3(T1 t1, T2 t2, T3 t3){
        this._1 = t1;
        this._2 = t2;
        this._3 = t3;

    }

    public <U> Tuple3<U, T2,T3> update1(U val) {
        return Tuple.of(val,_2,_3);
    }
    public <U> Tuple3<T1,U,T3> update2(U val) {
        return Tuple.of(_1,val,_3);
    }
    public <U> Tuple3<T1,T2,U> update3(U val) {
        return Tuple.of(_1,_2,val);
    }
    @Override
    public Object get(int index) {
        if (index == 0) {
            return Tuple0.INS;
        }
        switch (index) {
            case 1: return _1;
            case 2: return _2;
            case 3: return _3;
            default:throw new IndexOutOfBoundsException();
        }
    }
    @Override
    public int arity() {
        return 3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Tuple3 tuple3 = (Tuple3) o;
        return Objects.equals(_1, tuple3._1)
                && Objects.equals(_2, tuple3._2)
                && Objects.equals(_3, tuple3._3);
    }
    @Override
    public Iterator<Tuple2<Integer,Object>> iterator() {
        return new ArrayIterator<>(_1, _2, _3);
    }
    @Override
    public int hashCode() {
        return Objects.hash(_1, _2, _3);
    }
}
