package com.jacek.grzegorczyk.neo4jdemo.services;

import com.jacek.grzegorczyk.neo4jdemo.data.entities.CompanyEntity;
import com.jacek.grzegorczyk.neo4jdemo.data.entities.WorkerEntity;
import com.jacek.grzegorczyk.neo4jdemo.data.exceptions.EntityNotFoundException;
import com.jacek.grzegorczyk.neo4jdemo.data.repositories.CompanyRepository;
import com.jacek.grzegorczyk.neo4jdemo.data.repositories.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyEntityServiceImpl implements CompanyEntityService {

    private final CompanyRepository companyRepository;
    private final WorkerRepository workerRepository;

    @Override
    public CompanyEntity create(CompanyEntity companyEntity) {
        return companyRepository.save(companyEntity);
    }

    public CompanyEntity create(CompanyEntity companyEntity, Supplier<Void> fn) {
        CompanyEntity saved = companyRepository.save(companyEntity);

        fn.get();

        return saved;
    }

    @Override
    public CompanyEntity get(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity with id: " + id + " not found!"));
    }

    @Override
    public void delete(String companyName) {
        CompanyEntity companyEntity = companyRepository.findByName(companyName);
        companyRepository.delete(companyEntity);
    }

    @Override
    public CompanyEntity hire(String companyName, WorkerEntity workerEntity) {
        CompanyEntity hiringCompany = companyRepository.findByName(companyName);
        WorkerEntity workerToHire = workerRepository.save(workerEntity);
        hiringCompany.getWorkers().add(workerToHire);

        return companyRepository.save(hiringCompany);
    }

    @Override
    public CompanyEntity layOff(String companyName, String workerName) {
        CompanyEntity hiringCompany = companyRepository.findByName(companyName);
        WorkerEntity workerToLayOff = workerRepository.findByName(workerName);
        hiringCompany.getWorkers().remove(workerToLayOff);

        return hiringCompany;
    }
}
