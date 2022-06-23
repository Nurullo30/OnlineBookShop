package com.company.Exceptions;

import java.io.IOException;

public class NoBookFoundException extends Exception {

    public NoBookFoundException() {
    }

    public NoBookFoundException(String message) {
        super(message);
    }

    public NoBookFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoBookFoundException(Throwable cause) {
        super(cause);
    }

    public NoBookFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
