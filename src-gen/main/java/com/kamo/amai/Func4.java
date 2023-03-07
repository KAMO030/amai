package com.kamo.amai;

import com.kamo.amai.control.Option;

@FunctionalInterface
public interface Func4<T1,T2,T3,T4,R> extends Func<Func4<T1,T2,T3,T4,R>>{


    R invoke(T1 t1, T2 t2,T3 t3, T4 t4);

    @Override
    default int arity() {
        return 4;
    }
    default Func1<Tuple4<T1,T2,T3,T4>,R> tuple(){
        return  (t)->this.invoke(t._1,t._2,t._3,t._4);
    }

    default Func4<T1,T2,T3,T4,Option<R>> lift(){
        return (v1,v2,v3,v4) -> Option.of(invoke(v1,v2,v3,v4));
    }

    default Func1<T1,Func1<T2,Func1<T3,Func1<T4,R>>>>curried(){
        return v1 -> v2 ->v3 -> v4 -> invoke(v1,v2,v3,v4);
    }
}
