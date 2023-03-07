package com.kamo.amai;

import com.kamo.amai.control.Option;
//@FunctionalInterface
public interface CheckFunc0<R> extends Func<CheckFunc0<R>> {

    R tryInvoke() throws Throwable;

    @Override
    default int arity() {
        return 0;
    }

    default CheckFunc1<Tuple0,R> tuple()throws Throwable{
        return  t-> tryInvoke();
    }


    default CheckFunc0<Option<R>> lift(){
        return () -> Option.of(tryInvoke());
    }

}
