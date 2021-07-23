package com.study.exceptions;

public class EmptyMessageException extends ServiceException{
    public EmptyMessageException(String message) {
        super(message);
    }

    public EmptyMessageException(String message, Throwable cause) {
        super(message, cause);
    }
}
