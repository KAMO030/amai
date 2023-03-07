package com.kamo.amai;

@FunctionalInterface
public interface CheckConsumer<R> {
    void tryInvoke(R val) throws Throwable;
}
