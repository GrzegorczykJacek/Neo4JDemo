package com.jacek.grzegorczyk.neo4jdemo.data.entities;

import com.jacek.grzegorczyk.neo4jdemo.data.converters.PropertiesToMapConverter;
import org.springframework.data.neo4j.core.schema.CompositeProperty;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Node("User")
public class WorkerEntity extends CommonEntity {

    private String name;

    @CompositeProperty(converter = PropertiesToMapConverter.class)
    private Map<String, Object> properties;

    @Relationship(type = "OWNS", direction = Relationship.Direction.OUTGOING)
    private Set<CarEntity> cars;

    public WorkerEntity() {
        this.properties = new HashMap<>();
    }
}
