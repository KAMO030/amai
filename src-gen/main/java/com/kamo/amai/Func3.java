package com.kamo.amai;

import com.kamo.amai.control.Option;

@FunctionalInterface
public interface Func3<T1,T2,T3,R> extends Func<Func3<T1,T2,T3,R>> {

    R invoke(T1 t1, T2 t2,T3 t3);

    @Override
    default int arity() {
        return 3;
    }
    default Func1<Tuple3<T1,T2,T3>,R> tuple(){
        return  (t)->this.invoke(t._1,t._2,t._3);
    }

    default Func3<T1,T2,T3, Option<R>> lift(){
        return (v1,v2,v3) -> Option.of(invoke(v1,v2,v3));
    }
    default Func1<T1,Func1<T2,Func1<T3,R>>>curried(){
        return v1 -> v2 ->v3 -> invoke(v1,v2,v3);
    }

}
