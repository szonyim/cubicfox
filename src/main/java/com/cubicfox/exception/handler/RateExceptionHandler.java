package com.cubicfox.exception.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@ControllerAdvice
public class RateExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;

    @Autowired
    public RateExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

//    @ExceptionHandler({ TransactionSystemException.class })
//    public ResponseEntity<RestResponseErrorMessage> handleConstraintViolation(Exception ex, WebRequest request) {
//        Throwable cause = ((TransactionSystemException) ex).getRootCause();
//        if (cause instanceof ConstraintViolationException) {
//            Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) cause).getConstraintViolations();
//            // do something here
//        }
//    }
}
