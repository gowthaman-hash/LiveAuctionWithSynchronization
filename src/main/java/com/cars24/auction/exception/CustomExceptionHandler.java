package com.cars24.auction.exception;

import com.cars24.auction.common.ApplicationConstants;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler  extends ResponseEntityExceptionHandler {

 @ExceptionHandler(BidNotAccepted.class)
    public final ResponseEntity<ErrorResponse> handleNotAccepted
                        (BidNotAccepted ex, WebRequest request)
    {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(ApplicationConstants.NOT_ACCEPTED, details);
        return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(BidAccepted.class)
    public final ResponseEntity<ErrorResponse> handleAccepted
                        (BidAccepted ex, WebRequest request)
    {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());

        ErrorResponse error = new ErrorResponse(ApplicationConstants.ACCEPTED, details);
        return new ResponseEntity<>(error, HttpStatus.CREATED);
    }

    @ExceptionHandler(ActionNotFound.class)
    public final ResponseEntity<ErrorResponse> handleActionNotFound
                        (ActionNotFound ex, WebRequest request)
    {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse(ApplicationConstants.NOT_FOUND, details);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
