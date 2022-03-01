package com.jacek.grzegorczyk.neo4jdemo.data.repositories;

import com.jacek.grzegorczyk.neo4jdemo.data.entities.CarEntity;
import com.jacek.grzegorczyk.neo4jdemo.data.entities.WorkerEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkerRepository extends Neo4jRepository<WorkerEntity, Long> {

    WorkerEntity findByName(String name);

//    @Query("MATCH(w:Worker) RETURN w")
//    List<WorkerEntity> findAllWorkersOwningCars();

    @Query("MATCH p = (f)<-[*]->(w:Worker)-[:OWNS]->(c:Car) RETURN w, collect(nodes(p)), collect(relationships(p))")
    List<WorkerEntity> findAllWorkersOwningCars();
}
