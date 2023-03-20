package com.energybox.backendcodingchallenge.exceptions;

import lombok.Getter;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.energybox.backendcodingchallenge.constants.EnergyBoxConstants.INVALID_INPUT_EXCEPTION_MESSAGE;

/**
 * @author Sravan on 3/19/2023
 * @project backend-coding-challenge
 */

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Getter
public class InvalidInputException extends RuntimeException {
    private final String resourceName;
    private final String fieldName;

    public InvalidInputException(final @NonNull String resourceName, final @Nullable String fieldName) {
        super(String.format(INVALID_INPUT_EXCEPTION_MESSAGE, resourceName, fieldName));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
    }
}
