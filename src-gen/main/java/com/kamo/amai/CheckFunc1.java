package com.kamo.amai;

import com.kamo.amai.control.Option;
@FunctionalInterface
public interface CheckFunc1<T, R>  extends Func<CheckFunc1<T,R>> {

    R tryInvoke(T val) throws Throwable;



    @Override
    default int arity() {
        return 1;
    }


    default CheckFunc1<Tuple1<T>,R> tuple(){
        return (t)->this.tryInvoke(t._1);
    }


    default CheckFunc1<T, Option<R>> lift(){
        return (v) -> Option.of(tryInvoke(v));
    }
}
