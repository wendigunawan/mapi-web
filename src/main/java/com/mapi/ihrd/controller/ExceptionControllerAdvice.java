package com.mapi.ihrd.controller;


import com.mapi.ihrd.exception.*;
import com.mapi.ihrd.response.ResponseError;
import com.mapi.ihrd.config.Constant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by wendi on 10-03-2017.
 */

@ControllerAdvice
public class ExceptionControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ResponseError> exceptionHandler(DataAccessException ex) {
        logger.error(ex.getMessage(), ex);
        ResponseError error = new ResponseError(Constant.ERROR_CODE_300, "Internal server error");
        return new ResponseEntity<ResponseError>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ResponseError> exceptionHandler(BusinessException ex) {
        ResponseError error = new ResponseError(Constant.ERROR_CODE_300, ex.getMessage());
        return new ResponseEntity<ResponseError>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidAccountException.class)
    public ResponseEntity<ResponseError> exceptionHandler(InvalidAccountException ex) {
        ResponseError error = new ResponseError(Constant.ERROR_CODE_301, ex.getMessage());
        return new ResponseEntity<ResponseError>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidParameterValueException.class)
    public ResponseEntity<ResponseError> exceptionHandler(InvalidParameterValueException ex) {
        ResponseError error = new ResponseError(Constant.ERROR_CODE_302, ex.getMessage());
        return new ResponseEntity<ResponseError>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ResponseError> exceptionHandler(DataNotFoundException ex) {
        ResponseError error = new ResponseError(Constant.ERROR_CODE_303, ex.getMessage());
        return new ResponseEntity<ResponseError>(error, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(InvalidSecurityCodeException.class)
    public ResponseEntity<ResponseError> exceptionHandler(InvalidSecurityCodeException ex) {
        ResponseError error = new ResponseError(Constant.ERROR_CODE_304, ex.getMessage());
        return new ResponseEntity<ResponseError>(error, HttpStatus.BAD_REQUEST);
    }


}
