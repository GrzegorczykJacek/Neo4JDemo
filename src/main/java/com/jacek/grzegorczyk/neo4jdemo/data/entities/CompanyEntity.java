package com.jacek.grzegorczyk.neo4jdemo.data.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Node(value = "Company")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CompanyEntity extends CommonEntity {

    private String name;

    @Relationship(type = "HIRES", direction = Relationship.Direction.OUTGOING)
    public Set<WorkerEntity> workers;

    public CompanyEntity(String name) {
        this.name = name;
        this.workers = new HashSet<>();
    }
}
