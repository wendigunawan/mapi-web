package com.mapi.ihrd.exception;

/**
 * Created by wendi on 10-03-2017.
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
