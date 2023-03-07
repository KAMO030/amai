package com.kamo.amai;

import com.kamo.amai.control.Option;

import java.util.function.Supplier;
@FunctionalInterface
public interface Func0<R> extends Func<Func0<R>>,Supplier<R> {
    R invoke();

    @Override
    default R get() {
        return invoke();
    }
    @Override
    default int arity() {
        return 0;
    }

    default Func1<Tuple0,R> tuple(){
        return  t->invoke();

    }

    default Func0<Option<R>> lift(){
        return () -> Option.of(invoke());
    }
}
