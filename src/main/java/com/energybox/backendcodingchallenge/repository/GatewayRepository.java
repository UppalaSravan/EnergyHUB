package com.energybox.backendcodingchallenge.repository;

import com.energybox.backendcodingchallenge.domain.Gateway;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * @author Sravan on 3/19/2023
 * @project backend-coding-challenge
 */

@Repository
public interface GatewayRepository extends Neo4jRepository<Gateway, Long> {

    @Query("MATCH (g:Gateway) RETURN g")
    Collection<Gateway> findAllGateways();

    Gateway findByName(String gateway);

    @Query("Match(gateway:Gateway)<-[:CONNECTED_TO]-(sensor:Sensor) where $sensorType In sensor.types Return DISTINCT gateway")
    List<Gateway> findAllGatewaysAssignedWithElectricSensors(@Param("sensorType") String sensorType);
}
