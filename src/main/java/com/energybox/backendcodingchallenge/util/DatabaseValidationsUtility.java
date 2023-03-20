package com.energybox.backendcodingchallenge.util;

import com.energybox.backendcodingchallenge.repository.GatewayRepository;
import com.energybox.backendcodingchallenge.repository.SensorRepository;
import com.energybox.backendcodingchallenge.domain.Sensor;
import com.energybox.backendcodingchallenge.exceptions.DataConstraintViolationException;
import com.energybox.backendcodingchallenge.exceptions.ResourceNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.lang.NonNull;

import static com.energybox.backendcodingchallenge.constants.EnergyBoxConstants.*;

/**
 * @author Sravan on 3/19/2023
 * @project backend-coding-challenge
 */

public class DatabaseValidationsUtility {

    private static final Logger logger = LogManager.getLogger(DatabaseValidationsUtility.class);

    public static void validateGateway(@NonNull final String gatewayName,
                                       @NonNull final GatewayRepository gatewayRepository)
            throws ResourceNotFoundException {
        if (gatewayRepository.findByName(gatewayName) == null) {
            logger.error("Gateway not found in the database for [{}] ", gatewayName);
            throw new ResourceNotFoundException(GATEWAY, gatewayName);
        }
        logger.info("Successfully validated gateway for [{}] ", gatewayName);
    }

    public static void validateSensor(@NonNull final String sensorName,
                                      @NonNull final SensorRepository sensorRepository) {

        if (sensorRepository.findByName(sensorName) == null) {
            logger.error("Sensor not found in the database for [{}] ", sensorName);
            throw new ResourceNotFoundException(SENSOR, sensorName);
        }
        logger.info("Successfully validated sensor for [{}] ", sensorName);
    }

    public static void validateSensorGatewayRelationship(@NonNull final String sensorName,
                                                         @NonNull final SensorRepository sensorRepository) {

        final Sensor sensor = sensorRepository.findByName(sensorName);
        if (sensor == null) {
            logger.error("Sensor not found in the database for [{}] ", sensorName);
            throw new ResourceNotFoundException(SENSOR, sensorName);
        } else if (sensor.getGateway() != null) {
            final String relationshipMsg = String.format(GATEWAY_SENSOR_RELATIONSHIP, sensorName, sensor.getGateway().getName());
            throw new DataConstraintViolationException(RELATIONSHIP, relationshipMsg);
        }
        logger.info("Successfully validated sensor and relationship for [{}] ", sensorName);
    }
}
