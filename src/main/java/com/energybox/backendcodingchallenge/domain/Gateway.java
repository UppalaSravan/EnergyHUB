package com.energybox.backendcodingchallenge.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sravan on 3/19/2023
 * @project backend-coding-challenge
 */

@Node
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gateway {

    @JsonIgnore
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @JsonIgnore
    @Relationship(type = "CONNECTED_TO", direction = Relationship.Direction.INCOMING)
    private List<Sensor> sensorList = new ArrayList<>();

    public Gateway(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
