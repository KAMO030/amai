package com.kamo.amai.control;

import com.kamo.amai.ErrWrapperException;
import com.kamo.amai.Val;

import java.util.Objects;
import java.util.function.Function;

public abstract class Result<T> implements Val<T,Result<T>> {
    private Result(){}

    public static<T> Result.Ok<T> ok(T val) {
        return new Ok(val);
    }
    public static<T extends Throwable> Result.Err<T> err(T err) {
        return new Err<>(err);
    }

    public <R> Result<T> let(Function<T, R> block) {
        return null;
    }

    public boolean isOk(){
        return this instanceof Ok;
    }
    public boolean isErr(){
        return this instanceof Err;
    }
    public T unwrap() {
        return unwrapElse(e-> {
            throw new ErrWrapperException(e);
        });
    }
    public T unwrapElse(Function<Throwable,T> block) {
        T val = this.get();
        if (this.isErr()) {
           return block.apply((Throwable) val);
        }
        return val;
    }
    public static class Ok<T> extends Result<T>{

        private final T val;

        private Ok(T val) {
            Objects.requireNonNull(val);
            this.val = val;
        }

        @Override
        public T get() {
            return val;
        }
    }
    public static class Err<T extends Throwable> extends Result<T>{
        private final T err;

        private Err(T err) {
            Objects.requireNonNull(err);
            this.err = err;
        }

        @Override
        public T get() {
            return err;
        }
    }
}
