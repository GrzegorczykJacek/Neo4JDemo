package com.jacek.grzegorczyk.neo4jdemo.services;

import com.jacek.grzegorczyk.neo4jdemo.data.entities.WorkerEntity;

import java.util.List;

public interface WorkerEntityService {

    List<WorkerEntity> findAllWorkersOwningCars();

}
