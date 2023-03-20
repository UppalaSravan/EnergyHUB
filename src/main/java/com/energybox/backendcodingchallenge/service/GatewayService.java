package com.energybox.backendcodingchallenge.service;

import com.energybox.backendcodingchallenge.repository.GatewayRepository;
import com.energybox.backendcodingchallenge.domain.Gateway;
import com.energybox.backendcodingchallenge.domain.Sensor;
import com.energybox.backendcodingchallenge.exceptions.DataConstraintViolationException;
import com.energybox.backendcodingchallenge.util.DatabaseValidationsUtility;
import lombok.NonNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.energybox.backendcodingchallenge.constants.EnergyBoxConstants.GATEWAY;

/**
 * @author Sravan on 3/19/2023
 * @project backend-coding-challenge
 */

@Service
public class GatewayService {

    private static final Logger logger = LogManager.getLogger(GatewayService.class);

    private static final String query = "Match(g:Gateway {name: $gatewayName})<-[:CONNECTED_TO]-(s:Sensor) Return s";

    private static final String GATEWAY_NAME = "gatewayName";

    private static final String ELECTRICITY = "electricity";

    @Autowired
    GatewayRepository gatewayRepository;

    @Autowired
    Neo4jTemplate neo4jTemplate;

    /**
     * This method returns list of all the gateway
     *
     * @return List of gateway from the database.
     */
    public List<Gateway> getAll() {
        return (List<Gateway>) gatewayRepository.findAllGateways();
    }

    /**
     * This method will create a gateway
     *
     * @param gateway object representing gateway node
     * @return gateway object representing created gateway node
     */
    public Gateway create(@NonNull final Gateway gateway) {
        try {
            final Gateway createdGateway = gatewayRepository.save(gateway);

            logger.info("Successfully created gateway: [{}]", gateway);

            return createdGateway;
        } catch (final DataIntegrityViolationException ex) {
            logger.error("Error occurred when creating Gateway with exception: [{}]", ex);
            throw new DataConstraintViolationException(GATEWAY, gateway.getName());
        }
    }

    /**
     * This method returns list of sensors CONNECTED_TO gateway.
     *
     * @param gateway object representing gateway node
     * @return list of sensors retrieved from database
     */
    public List<Sensor> getSensorsForGateway(@NonNull final Gateway gateway) {
        DatabaseValidationsUtility.validateGateway(gateway.getName(), gatewayRepository);

        //Construct query and params to find all sensors from gateway
        final Map<String, Object> params = new HashMap<>();
        params.put(GATEWAY_NAME, gateway.getName());

        final List<Sensor> sensors = neo4jTemplate.findAll(query, params, Sensor.class);
        logger.info("Successfully retrieved sensors :[{}] assigned with the gateway :[{}]", sensors, gateway);
        return sensors;
    }

    /**
     * This method return list of all gateways assigned with sensors having type electricity
     *
     * @return list of gateways from database
     */
    public List<Gateway> getAllGatewaysAssignedWithElectricSensors() {
        return gatewayRepository.findAllGatewaysAssignedWithElectricSensors(ELECTRICITY);
    }
}
