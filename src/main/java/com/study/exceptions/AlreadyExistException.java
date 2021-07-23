package com.study.exceptions;

public class AlreadyExistException extends ServiceException{
    public AlreadyExistException(String message) {
        super(message);
    }

    public AlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
