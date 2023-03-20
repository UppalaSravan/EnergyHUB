package com.energybox.backendcodingchallenge.exceptions;

import lombok.Getter;
import lombok.NonNull;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.energybox.backendcodingchallenge.constants.EnergyBoxConstants.RESOURCE_EXISTS_EXCEPTION_MESSAGE;

/**
 * @author Sravan on 3/19/2023
 * @project backend-coding-challenge
 */

@ResponseStatus(value = HttpStatus.CONFLICT)
@Getter
public class DataConstraintViolationException extends RuntimeException {

    private final String resourceName;
    private final String fieldName;

    public DataConstraintViolationException(final @NonNull String resourceName, final @NonNull String fieldName) {
        super(String.format(RESOURCE_EXISTS_EXCEPTION_MESSAGE, resourceName, fieldName));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
    }
}
