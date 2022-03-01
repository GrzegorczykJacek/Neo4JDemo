package com.jacek.grzegorczyk.neo4jdemo.data.entities;

import com.jacek.grzegorczyk.neo4jdemo.data.converters.PropertiesToMapConverter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.CompositeProperty;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Node("Car")
@Getter
@Setter
public class CarEntity extends CommonEntity {

    private String brand;

    @CompositeProperty(converter = PropertiesToMapConverter.class)
    private Map<String, Object> properties;

    @Relationship(type = "OWNS", direction = Relationship.Direction.INCOMING)
    public Set<WorkerEntity> owners;

    public CarEntity() {
        this.properties = new HashMap<>();
    }

}
