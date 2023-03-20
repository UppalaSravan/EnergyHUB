package com.energybox.backendcodingchallenge.service;


import com.energybox.backendcodingchallenge.repository.SensorRepository;
import com.energybox.backendcodingchallenge.domain.Sensor;
import com.energybox.backendcodingchallenge.exceptions.DataConstraintViolationException;
import lombok.NonNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.energybox.backendcodingchallenge.constants.EnergyBoxConstants.SENSOR;

/**
 * @author Sravan on 3/19/2023
 * @project backend-coding-challenge
 */

@Service
public class SensorService {

    private static final Logger logger = LogManager.getLogger(SensorService.class);

    @Autowired
    SensorRepository sensorRepository;

    /**
     * This method returns list of all sensor
     *
     * @return list of sensor from the database
     */
    public List<Sensor> getAllSensors() {
        return sensorRepository.findAll();
    }

    /**
     * This method will create the sensor
     *
     * @param sensor object representing sensor node
     * @return sensor object representing created sensor node
     */
    public Sensor create(@NonNull final Sensor sensor) {
        try {
            final Sensor createdSensor = sensorRepository.save(sensor);

            logger.info("Successfully created sensor: [{}]", sensor);

            return createdSensor;
        } catch (final DataIntegrityViolationException ex) {
            logger.error("Error occurred when creating Sensor with exception: [{}]", ex);
            throw new DataConstraintViolationException(SENSOR, sensor.getName());
        }
    }

    /**
     * This method returns list of all sensors of certain type
     *
     * @param sensorType String representing the type of sensor node
     * @return List of sensor from the database
     */
    public List<Sensor> getSensorsByType(final String sensorType) {
        final List<Sensor> sensorList = sensorRepository.findAllSensorsWithType(sensorType);
        logger.info("Successfully retrieved [{}] sensors: [{}]", sensorType, sensorList);
        return sensorList;
    }
}