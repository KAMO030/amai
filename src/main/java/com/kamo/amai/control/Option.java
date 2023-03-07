package com.kamo.amai.control;

import com.kamo.amai.Tuple0;
import com.kamo.amai.Val;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

public  abstract class Option<T> implements  Val<T,Option<T>> {
    private Option(){}
    public static<T> Option<T> of(T val) {
        return val == null ?  Nome.INS : new Some<>(val);
    }
    public boolean exists(){
        return this instanceof Some;
    }

    public Option<T> also(Consumer<T> block) {
        Objects.requireNonNull(block);
        block.accept(get());
        return this;
    }


    public <R> Option<R> let(Function<T, R> block) {
        return Option.of(block.apply(get()));
    }



    public static class Some<T>extends Option<T>{

        private final T val;

        private Some(T val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "Option.Some{" +
                    "val=" + val +
                    '}';
        }

        @Override
        public T get() {
            return val;
        }
    }
    public static class Nome extends Option{

        private static final Nome INS = new Nome();


        private Nome() { if (Objects.nonNull(INS))throw new IllegalStateException(); }

        @Override
        public String toString() {
            return "Option.Nome";
        }

        @Override
        public Tuple0 get() {
            return Tuple0.INS;
        }
    }

}
