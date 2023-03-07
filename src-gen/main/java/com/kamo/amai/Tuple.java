package com.kamo.amai;

import java.util.StringJoiner;

public abstract class Tuple<S extends Tuple>  implements Iterable<Tuple2<Integer,Object>>, Any<S> {

    public static Tuple0 of() {
        return Tuple0.INS;
    }

    public static <T1> Tuple1<T1> of(T1 t1) {
        return new Tuple1<>(t1);
    }

    public static <T1, T2> Tuple2<T1, T2> of(T1 t1, T2 t2) {
        return new Tuple2<>(t1, t2);
    }

    public static <T1, T2, T3> Tuple3<T1, T2, T3> of(T1 t1, T2 t2, T3 t3) {
        return new Tuple3<>(t1, t2, t3);
    }

    public static <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4> of(T1 t1, T2 t2, T3 t3, T4 t4) {
        return new Tuple4<>(t1, t2, t3, t4);
    }

    @Override
    public String toString() {
        StringJoiner join = new StringJoiner(",", "(", ")");
        for (int i = 1; i <= arity(); i++) {
            join.add(this.get(i).toString());
        }
        return join.toString();
    }



    abstract Object get(int index);

    abstract int arity();
}
