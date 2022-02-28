package com.jacek.grzegorczyk.neo4jdemo.data.repositories;

import com.jacek.grzegorczyk.neo4jdemo.data.entities.CompanyEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends Neo4jRepository<CompanyEntity, Long> {

    CompanyEntity findByName(String name);

    Integer countByName(String name);

}
