package com.jacek.grzegorczyk.neo4jdemo.services;

import com.jacek.grzegorczyk.neo4jdemo.data.entities.CompanyEntity;
import com.jacek.grzegorczyk.neo4jdemo.data.entities.WorkerEntity;

public interface CompanyEntityService {
    CompanyEntity create(CompanyEntity companyEntity);

    CompanyEntity get(Long id);

    void delete(CompanyEntity companyEntity);

    CompanyEntity hire(String companyName, WorkerEntity workerEntity);

    CompanyEntity layOff(String companyName, String workerName);
}
