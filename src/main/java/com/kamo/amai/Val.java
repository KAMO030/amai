package com.kamo.amai;

import java.util.Objects;
import java.util.function.Consumer;

public interface Val<T,S extends Val> extends Any<S> {

    T get();
    default Val<T,S> also(Consumer<T> block) {
        Objects.requireNonNull(block);
        T val = get();
        block.accept(val);
        return this;
    }



    default Class<T> javaClass(){
        return (Class<T>) get().getClass();
    }
}
