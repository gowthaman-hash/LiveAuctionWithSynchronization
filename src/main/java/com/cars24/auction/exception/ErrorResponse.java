package com.cars24.auction.exception;

import java.util.List;

public class ErrorResponse {
    private String message;
    private List<String> details;
    public ErrorResponse(String message, List<String> details) {
        super();
        this.message = message;
        this.details = details;
    }


}
