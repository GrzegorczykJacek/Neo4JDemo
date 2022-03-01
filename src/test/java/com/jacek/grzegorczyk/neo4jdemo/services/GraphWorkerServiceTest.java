package com.jacek.grzegorczyk.neo4jdemo.services;

import com.jacek.grzegorczyk.neo4jdemo.AbstractIntegrationTest;
import com.jacek.grzegorczyk.neo4jdemo.data.entities.WorkerEntity;
import com.jacek.grzegorczyk.neo4jdemo.data.repositories.WorkerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.springframework.util.Assert.isTrue;

class GraphWorkerServiceTest extends AbstractIntegrationTest {

    @Autowired
    WorkerEntityServiceImpl workerEntityService;

    @Autowired
    WorkerRepository workerRepository;

    @Test
    void shouldReturnWorkersThatOwnCar() {
        // Given

        // When
        List<WorkerEntity> allWorkersOwningCars = workerEntityService.findAllWorkersOwningCars();

        // Then
        isTrue(2 == allWorkersOwningCars.size(), "should be exactly three owners");

    }
}