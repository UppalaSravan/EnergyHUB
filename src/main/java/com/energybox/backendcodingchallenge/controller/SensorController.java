package com.energybox.backendcodingchallenge.controller;

import com.energybox.backendcodingchallenge.domain.Sensor;
import com.energybox.backendcodingchallenge.domain.SensorGatewayAssignInput;
import com.energybox.backendcodingchallenge.service.SensorGatewayRelationshipService;
import com.energybox.backendcodingchallenge.service.SensorService;
import com.energybox.backendcodingchallenge.util.InputValidationsUtility;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.energybox.backendcodingchallenge.constants.EnergyBoxConstants.*;

/**
 * @author Sravan on 3/19/2023
 * @project backend-coding-challenge
 */

@RestController
@RequestMapping(value = "/sensors")
public class SensorController {

    private static final Logger logger = LogManager.getLogger(GatewayController.class);

    @Autowired
    SensorService sensorService;

    @Autowired
    SensorGatewayRelationshipService sensorGatewayRelationshipService;

    @ApiOperation(value = "Get all sensors")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Sensor>> getAllSensors() {
        return new ResponseEntity<>(sensorService.getAllSensors(), HttpStatus.OK);
    }

    @ApiOperation(value = "Assign a sensor to gateway")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Resource created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Resource not found"),
            @ApiResponse(code = 409, message = "Conflict in creating request"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @RequestMapping(value = "/assign/gateway", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Sensor> assignToGateway(@RequestBody final SensorGatewayAssignInput sensorGatewayAssignInput) {
        logger.info("Received input to assign sensor: [{}] to gateway: [{}]",
                sensorGatewayAssignInput.getSensor(), sensorGatewayAssignInput.getGateway());
        InputValidationsUtility.validateInputAndThrow(GATEWAY, sensorGatewayAssignInput.getGateway());
        InputValidationsUtility.validateInputAndThrow(SENSOR, sensorGatewayAssignInput.getSensor());
        return new ResponseEntity<>(sensorGatewayRelationshipService.assignSensorToGateway(sensorGatewayAssignInput),
                HttpStatus.CREATED);
    }

    @ApiOperation(value = "create a new sensor")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Resource created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 409, message = "Conflict in creating request"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @RequestMapping(value = "/", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Sensor> create(@RequestBody final Sensor sensor) {
        logger.info("Received input to create sensor: [{}]", sensor);
        InputValidationsUtility.validateSensor(sensor);
        return new ResponseEntity<>(sensorService.create(sensor), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get sensors of certain type")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @RequestMapping(value = "/type/{sensorType}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Sensor>> getSensorsByType(@PathVariable final String sensorType) {
        InputValidationsUtility.validateInputAndThrow(SENSOR_TYPE, sensorType);
        return new ResponseEntity<>(sensorService.getSensorsByType(sensorType), HttpStatus.OK);
    }
}
