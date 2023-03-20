package com.energybox.backendcodingchallenge.constants;

/**
 * @author Sravan on 3/19/2023
 * @project backend-coding-challenge
 */
public interface EnergyBoxConstants {
    String NOT_FOUND_EXCEPTION_MESSAGE = "%s not found with name: '%s'";
    String RESOURCE_EXISTS_EXCEPTION_MESSAGE = "%s already exists with: '%s'";
    String INVALID_INPUT_EXCEPTION_MESSAGE = "Invalid input given for %s : '%s'";
    String SENSOR = "Sensor";
    String GATEWAY = "Gateway";
    String RELATIONSHIP = "Relationship";
    String GATEWAY_SENSOR_RELATIONSHIP = "Sensor->Gateway: %s->%s";
    String SENSOR_TYPE = "sensorType";
}
