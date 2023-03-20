package com.energybox.backendcodingchallenge.exceptions;

import lombok.Data;

import java.util.Date;

/**
 * @author Sravan on 3/19/2023
 * @project backend-coding-challenge
 */

@Data
public class ErrorDetails {
    private Date timestamp;
    private String exceptionName;
    private String message;
    private String details;

    public ErrorDetails(Date timestamp, String exceptionName, String message, String details) {
        this.timestamp = timestamp;
        this.exceptionName = exceptionName;
        this.message = message;
        this.details = details;
    }
}
