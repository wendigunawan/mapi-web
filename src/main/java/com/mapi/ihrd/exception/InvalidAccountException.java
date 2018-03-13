package com.mapi.ihrd.exception;

/**
 * Created by wendi on 10-03-2017.
 */
public class InvalidAccountException extends BusinessException {

    public InvalidAccountException(String message) {
        super(message);
    }

    public InvalidAccountException(String message, Throwable cause) {
        super(message, cause);
    }
}
