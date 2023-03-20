package com.energybox.backendcodingchallenge.repository;

import com.energybox.backendcodingchallenge.domain.Sensor;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Sravan on 3/19/2023
 * @project backend-coding-challenge
 */

@Repository
public interface SensorRepository extends Neo4jRepository<Sensor, Long> {

    Sensor findByName(String sensor);

    @Query("MATCH (gateway:Gateway), (sensor:Sensor) " +
            "WHERE gateway.name = $gatewayName AND sensor.name = $sensorName " +
            "CREATE (sensor)-[:CONNECTED_TO]->(gateway) Return sensor")
    Sensor connectToGateway(@Param("sensorName") String sensorName, @Param("gatewayName") String gatewayName);

    @Query("Match(sensor:Sensor) Where $sensorType IN sensor.types Return sensor")
    List<Sensor> findAllSensorsWithType(@Param("sensorType") String sensorType);
}
