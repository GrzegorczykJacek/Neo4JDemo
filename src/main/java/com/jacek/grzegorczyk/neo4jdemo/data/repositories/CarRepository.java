package com.jacek.grzegorczyk.neo4jdemo.data.repositories;

import com.jacek.grzegorczyk.neo4jdemo.data.entities.CarEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends Neo4jRepository<CarEntity, Long> {

    Integer countByBrand(String brand);

    @Query("MATCH p = (w:Worker)-[:OWNS]->(c:Car) WHERE c.brand = $brand AND w.name STARTS WITH $owner " +
            "RETURN c")
    List<CarEntity> findCarEntitiesByBrandAndOwnerThatStartsWith(@Param("brand") String brand, @Param("owner") String owner);

}
