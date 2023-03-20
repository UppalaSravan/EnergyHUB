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
public class Sensor {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    private String name;

    private List<String> types = new ArrayList<>();

    @JsonIgnore
    @Relationship(type = "CONNECTED_TO", direction = Relationship.Direction.OUTGOING)
    private Gateway gateway;

    public Sensor(Long id, String name, List<String> types) {
        this.id = id;
        this.name = name;
        this.types = types;
    }
}
