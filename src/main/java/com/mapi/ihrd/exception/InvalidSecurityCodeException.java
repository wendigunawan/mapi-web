package com.mapi.ihrd.exception;

/**
 * Created by wendi on 23-03-2017.
 */
public class InvalidSecurityCodeException extends BusinessException {

    public InvalidSecurityCodeException(String message) {
        super(message);
    }

    public InvalidSecurityCodeException(String message, Throwable cause) {
        super(message, cause);
    }
}
