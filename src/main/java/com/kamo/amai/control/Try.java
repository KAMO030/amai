package com.kamo.amai.control;


import com.kamo.amai.CheckConsumer;
import com.kamo.amai.CheckFunc0;
import com.kamo.amai.CheckFunc1;

import java.util.Objects;
import java.util.function.Consumer;
@FunctionalInterface
public interface Try<T> {


    Result<T> run();

    static <T> Try<T> of(CheckFunc0<T> block) {
        return () -> {
            try {
                return Result.ok(block.tryInvoke());
            } catch (Throwable e) {
                return (Result<T>) Result.err(e);
            }
        };
    }

    default Try<T> onFinal(CheckConsumer<T> block) {
        return () -> {
            Result<T> result = null;
            try {
                result = this.run();
                return result;
            } finally {
                T val = null;
                if (Objects.nonNull(result) && (result.isOk())) {
                    val = result.get();
                }
                try {
                    block.tryInvoke(val);
                } catch (Throwable e) {
                    return (Result<T>) Result.err(e);
                }
            }
        };
    }

    default Try<T> andThen(Consumer<T> block) {
        return () -> {
            Result<T> result = this.run();
            if (result.isOk()) {
                block.accept(result.get());
            }
            return result;
        };
    }

    default Try<T> onErr(CheckConsumer<Throwable> block) {
        return onErr((CheckFunc1<Throwable, T>) throwable -> {
            block.tryInvoke(throwable);
            throw throwable;
        });
    }

    default Try<T> onErr(CheckFunc1<Throwable, T> block) {
        return onErr(Throwable.class, block);
    }

    default Try<T> onErr(Class<? extends Throwable> eType, CheckFunc1<Throwable, T> block) {
        return () -> {
            try {
                Result<T> result = this.run();
                if (result.isErr()) {
                    throw (Throwable) result.get();
                }
                return result;
            } catch (Throwable e) {
                try {
                    eType.cast(e);
                    return Result.ok(block.tryInvoke(e));
                } catch (Throwable ex) {
                    Throwable err = (ex instanceof ClassCastException) ? e : ex;
                    return (Result<T>) Result.err(err);
                }
            }
        };
    }

}
