package com.jacek.grzegorczyk.neo4jdemo.services;

import com.jacek.grzegorczyk.neo4jdemo.data.entities.WorkerEntity;
import com.jacek.grzegorczyk.neo4jdemo.data.repositories.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class WorkerEntityServiceImpl implements WorkerEntityService {

    private final WorkerRepository workerRepository;

    @Override
    public List<WorkerEntity> findAllWorkersOwningCars() {
        return workerRepository.findAllWorkersOwningCars();
    }
}
