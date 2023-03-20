package com.energybox.backendcodingchallenge.controller;

import com.energybox.backendcodingchallenge.domain.Sensor;
import com.energybox.backendcodingchallenge.service.SensorGatewayRelationshipService;
import com.energybox.backendcodingchallenge.service.SensorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class SensorControllerTest {

    @Mock
    SensorService sensorService;

    @Mock
    SensorGatewayRelationshipService sensorGatewayRelationshipService;

    SensorController sensorController;

    @Test
    public void testGetAllSensors() {
        List<Sensor> sensors = List.of(new Sensor(1L, "sensor1",  null),
                new Sensor(2L, "sensor2", null));
        when(sensorService.getAllSensors()).thenReturn(sensors);

        ResponseEntity<List<Sensor>> responseEntity = sensorController.getAllSensors();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(sensors, responseEntity.getBody());
    }
}
