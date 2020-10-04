package com.cars24.auction.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CREATED)
public class BidAccepted extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BidAccepted(String message) {
        super(message);
    }
}