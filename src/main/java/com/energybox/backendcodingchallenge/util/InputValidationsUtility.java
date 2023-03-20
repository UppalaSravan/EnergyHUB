package com.energybox.backendcodingchallenge.util;

import com.energybox.backendcodingchallenge.domain.Gateway;
import com.energybox.backendcodingchallenge.domain.Sensor;
import com.energybox.backendcodingchallenge.exceptions.InvalidInputException;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.energybox.backendcodingchallenge.constants.EnergyBoxConstants.*;

/**
 * @author Sravan on 3/19/2023
 * @project backend-coding-challenge
 */

public class InputValidationsUtility {

    private static final Logger logger = LogManager.getLogger(InputValidationsUtility.class);

    public static void validateGateway(final Gateway gateway) {
        validateInputAndThrow(GATEWAY, gateway.getName());
    }

    public static void validateSensor(final Sensor sensor) {
        validateInputAndThrow(SENSOR, sensor.getName());
        for (String type : sensor.getTypes()) {
            validateInputAndThrow(SENSOR_TYPE, type);
        }
    }

    public static void validateInputAndThrow(final String resource, final String input) throws InvalidInputException {
        if (StringUtils.isBlank(input)) {
            logger.error(String.format(INVALID_INPUT_EXCEPTION_MESSAGE, resource, input));
            throw new InvalidInputException(resource, input);
        }
    }
}
