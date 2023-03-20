package com.energybox.backendcodingchallenge.service;

import com.energybox.backendcodingchallenge.repository.GatewayRepository;
import com.energybox.backendcodingchallenge.repository.SensorRepository;
import com.energybox.backendcodingchallenge.domain.Sensor;
import com.energybox.backendcodingchallenge.domain.SensorGatewayAssignInput;
import com.energybox.backendcodingchallenge.util.DatabaseValidationsUtility;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Service;

/**
 * @author Sravan on 3/19/2023
 * @project backend-coding-challenge
 */

@Service
public class SensorGatewayRelationshipService {

    @Autowired
    SensorRepository sensorRepository;

    @Autowired
    GatewayRepository gatewayRepository;

    @Autowired
    Neo4jClient neo4jClient;

    /**
     * This method will assign 'CONNECTED_TO' relationship between given sensor to gateway
     *
     * @param sensorGatewayAssignInput object representing both gateway node and sensor node
     * @return sensor object from database after successfully establishing the relationship between sensor and gateway
     */
    public Sensor assignSensorToGateway(@NonNull final SensorGatewayAssignInput sensorGatewayAssignInput) {
        DatabaseValidationsUtility.validateGateway(sensorGatewayAssignInput.getGateway(), gatewayRepository);
        DatabaseValidationsUtility.validateSensorGatewayRelationship(sensorGatewayAssignInput.getSensor(), sensorRepository);

        return sensorRepository.connectToGateway(sensorGatewayAssignInput.getSensor(), sensorGatewayAssignInput.getGateway());
    }
}
