package com.mapi.ihrd.exception;

/**
 * Created by wendi on 10-03-2017.
 */
public class InvalidParameterValueException extends BusinessException {

    public InvalidParameterValueException(String message) {
        super(message);
    }

    public InvalidParameterValueException(String message, Throwable cause) {
        super(message, cause);
    }
}
