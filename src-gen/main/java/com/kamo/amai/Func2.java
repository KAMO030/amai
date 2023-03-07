package com.kamo.amai;

import com.kamo.amai.control.Option;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
@FunctionalInterface
public interface Func2 <T1,T2,R> extends Func<Func2 <T1,T2,R>>,BiFunction<T1,T2,R>, BiConsumer<T1,T2> {



    R invoke(T1 t1, T2 t2);

    @Override
    default void accept(T1 t1, T2 t2){

    }

    @Override
    default int arity() {
        return 2;
    }

    default Func1<Tuple2<T1,T2>,R> tuple(){
        return  (t)->this.invoke(t._1,t  ._2);
    }

    @Override
    default R apply(T1 t1, T2 t2) {
        return invoke(t1,t2);
    }
    @Override
    default <V> Func2<T1, T2, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T1 v1, T2 v2)-> after.apply(apply(v1, v2));
    }

    @Override
    default Func2<T1, T2,Tuple0> andThen(BiConsumer<? super T1, ? super T2> after) {
        Objects.requireNonNull(after);
        return (l, r) -> {
            accept(l, r);
            after.accept(l, r);
            return Tuple0.INS;
        };
    }

    default <V> Func2<T1, T2, V> andThen(Func1<? super R, ? extends V> after) {
        return andThen((Function<? super R, ? extends V>)after);
    }
    default Func2<T1, T2, Option<R>> lift(){
        return (v1,v2) -> Option.of(invoke(v1,v2));
    }
    default Func1<T1,Func1<T2,R>>curried(){
        return v1 -> v2 -> invoke(v1,v2);
    }


}
