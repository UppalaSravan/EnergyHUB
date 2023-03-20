package com.energybox.backendcodingchallenge.controller;

import com.energybox.backendcodingchallenge.domain.Gateway;
import com.energybox.backendcodingchallenge.domain.Sensor;
import com.energybox.backendcodingchallenge.service.GatewayService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import static com.energybox.backendcodingchallenge.constants.EnergyBoxConstants.GATEWAY;

/**
 * @author Sravan on 3/19/2023
 * @project backend-coding-challenge
 */
@RestController
@RequestMapping(value = "/gateways")
public class GatewayController {

    private static final Logger logger = LogManager.getLogger(GatewayController.class);

    @Autowired
    private GatewayService gatewayService;

    @ApiOperation(value = "create a new gateway")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Resource created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 409, message = "Conflict in creating request"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @RequestMapping(value = "/", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Gateway> create(@RequestBody Gateway gateway) throws IOException, InterruptedException {
        logger.info("Received input to create sensor: [{}]", gateway);
        InputValidationsUtility.validateGateway(gateway);
        return new ResponseEntity<>(gatewayService.create(gateway), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get all gateways")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Gateway>> getAllGateways() {
        return new ResponseEntity<>(gatewayService.getAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get all sensors assigned to gateway")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Resource not found"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @RequestMapping(value = "/sensors", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Sensor>> getSensorsForGateway(@RequestBody Gateway gateway) {
        logger.info("Received input to get sensors for Gateway: [{}]", gateway.getName());
        InputValidationsUtility.validateInputAndThrow(GATEWAY, gateway.getName());
        return new ResponseEntity<>(gatewayService.getSensorsForGateway(gateway), HttpStatus.OK);
    }

    @ApiOperation(value = "Get gateways assigned with electrical sensors")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @RequestMapping(value = "/sensors/type/electric", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Gateway>> getAllGatewaysAssignedWithElectricSensors() {
        return new ResponseEntity<>(gatewayService.getAllGatewaysAssignedWithElectricSensors(), HttpStatus.OK);
    }
}
