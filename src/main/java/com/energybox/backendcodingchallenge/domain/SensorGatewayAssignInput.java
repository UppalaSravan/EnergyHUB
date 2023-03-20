package com.energybox.backendcodingchallenge.domain;

import lombok.Data;

/**
 * @author Sravan on 3/19/2023
 * @project backend-coding-challenge
 */

@Data
public class SensorGatewayAssignInput {

    private String gateway;
    private String sensor;
}
