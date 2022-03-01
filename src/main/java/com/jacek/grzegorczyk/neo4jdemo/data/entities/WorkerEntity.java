package com.jacek.grzegorczyk.neo4jdemo.data.entities;

import com.jacek.grzegorczyk.neo4jdemo.data.converters.PropertiesToMapConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.CompositeProperty;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Node("Worker")
@AllArgsConstructor
@Getter
@Setter
public class WorkerEntity extends CommonEntity {

    private String name;

    @CompositeProperty(converter = PropertiesToMapConverter.class)
    private Map<String, Object> properties;

    @Relationship(type = "OWNS", direction = Relationship.Direction.OUTGOING)
    private Set<CarEntity> cars;

    @Relationship(type = "FRIENDS", direction = Relationship.Direction.OUTGOING)
    private Set<WorkerEntity> friends;

    public WorkerEntity() {
        this.properties = new HashMap<>();
        this.friends = new HashSet<>();
    }
}
