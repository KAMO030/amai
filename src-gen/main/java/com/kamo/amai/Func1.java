package com.kamo.amai;



import com.kamo.amai.control.Option;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
@FunctionalInterface
public interface Func1<T,R> extends Func<Func1<T,R>>, Function<T,R>, Consumer<T> {

    R invoke(T val);


    @Override
    default int arity() {
        return 1;
    }

    default Func1<Tuple1<T>,R> tuple(){
        return (t)->this.invoke(t._1);
    }

    @Override
    default <V> Func1<V, R> compose(Function<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return (V v) -> apply(before.apply(v));
    }
    @Override
    default void accept(T val) {
        invoke(val);
    }

    @Override
    default R apply(T val) {
        return invoke(val);
    }
    @Override
    default <V> Func1<T, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t) -> after.apply(apply(t));
    }

    @Override
    default Func1<T, Tuple0> andThen(Consumer<? super T> after) {
        Objects.requireNonNull(after);
        return (T t) -> { accept(t); after.accept(t);return Tuple0.INS; };
    }

    default Func1<T,Option<R>> lift(){
        return (v) -> Option.of(invoke(v));
    }
}
