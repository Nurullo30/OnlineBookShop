package com.company.Exceptions;

public class NoBookSavedException extends Exception {

    public NoBookSavedException() {
    }

    public NoBookSavedException(String message) {
        super(message);
    }

    public NoBookSavedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoBookSavedException(Throwable cause) {
        super(cause);
    }

    public NoBookSavedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
