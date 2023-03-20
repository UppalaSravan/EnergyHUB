package com.energybox.backendcodingchallenge.exceptions;

import lombok.Getter;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.energybox.backendcodingchallenge.constants.EnergyBoxConstants.NOT_FOUND_EXCEPTION_MESSAGE;

/**
 * @author Sravan on 3/19/2023
 * @project backend-coding-challenge
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Getter
public class ResourceNotFoundException extends RuntimeException {
    private final String resourceName;
    private final String fieldName;

    public ResourceNotFoundException(final @NonNull String resourceName, final @NonNull String fieldName) {
        super(String.format(NOT_FOUND_EXCEPTION_MESSAGE, resourceName, fieldName));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
    }
}
