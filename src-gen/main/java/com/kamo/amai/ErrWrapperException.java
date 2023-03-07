package com.kamo.amai;

public class ErrWrapperException extends RuntimeException {
    public ErrWrapperException(Throwable cause) {
        super(cause);
    }
}
