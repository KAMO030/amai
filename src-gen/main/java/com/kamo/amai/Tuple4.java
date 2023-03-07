package com.kamo.amai;

import com.kamo.amai.collection.TupleIterator;

import java.util.Iterator;
import java.util.Objects;

public class Tuple4<T1,T2,T3,T4> extends Tuple<Tuple4<T1,T2,T3,T4>> {
    public final T1 _1;
    public final T2 _2;
    public final T3 _3;
    public final T4 _4;
    public Tuple4(T1 t1, T2 t2,T3 t3,T4 t4){
        this._1 = t1;
        this._2 = t2;
        this._3 = t3;
        this._4 = t4;
    }

    public <U> Tuple4<U, T2,T3,T4> update1(U val) {
        return Tuple.of(val,_2,_3,_4);
    }
    public <U> Tuple4<T1, U,T3,T4> update2(U val) {
        return Tuple.of(_1,val,_3,_4);
    }

    public <U> Tuple4<T1,T2,U,T4> update3(U val) {
        return Tuple.of(_1,_2,val,_4);
    }

    public <U> Tuple4< T1,T2,T3,U> update4(U val) {
        return Tuple.of(_1,_2,_3,val);
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
            case 4: return _4;
           default:throw new IndexOutOfBoundsException();
        }
    }
    @Override
    public int arity() {
        return 4;
    }


    @Override
    public boolean equals(Object o) {

        Tuple4 tuple4 = (Tuple4) o;
        return Objects.equals(_1, tuple4._1)
                && Objects.equals(_2, tuple4._2)
                && Objects.equals(_3, tuple4._3)
                && Objects.equals(_4, tuple4._4);
    }
    @Override
    public Iterator<Tuple2<Integer,Object>> iterator() {
        return new TupleIterator(_1, _2, _3, _4);
    }
    @Override
    public int hashCode() {
        return Objects.hash(_1, _2, _3, _4);
    }
}
