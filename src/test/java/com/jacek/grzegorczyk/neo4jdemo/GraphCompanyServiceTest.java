package com.jacek.grzegorczyk.neo4jdemo;

import com.jacek.grzegorczyk.neo4jdemo.data.entities.CompanyEntity;
import com.jacek.grzegorczyk.neo4jdemo.data.repositories.CompanyRepository;
import com.jacek.grzegorczyk.neo4jdemo.services.CompanyEntityServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.function.Supplier;

import static org.springframework.util.Assert.*;

class GraphCompanyServiceTest extends AbstractIntegrationTest {
    public static final Supplier<Void> EXCEPTION_SUPPLIER = () -> {
        throw new RuntimeException("this should fail");
    };

    @Autowired
    CompanyEntityServiceImpl companyService;

    @Autowired
    CompanyRepository companyRepository;

    @Test
    void shouldSaveNewNodeToNeo4J() {
        // given
        CompanyEntity companyEntity = new CompanyEntity(FAKER.funnyName().name());

        // when
        CompanyEntity createdCompany = companyService.create(companyEntity);

        // then
        notNull(createdCompany, "should be created");
        notNull(createdCompany.getId(), "should have an id");

        isTrue(1 == companyRepository.countByName(companyEntity.getName()), "should be exactly one Company");
    }

    @Test
    void shouldRollbackDueToException() {
        // given
        CompanyEntity companyEntity = null;
        CompanyEntity newCompanyEntity = new CompanyEntity(FAKER.funnyName().name());

        // when
        try {
            companyEntity = companyService.create(newCompanyEntity, EXCEPTION_SUPPLIER);
        } catch (Exception exception) {

        }

        // then
        isNull(companyEntity, "shouldn't be created");
        Integer count = companyRepository.countByName(newCompanyEntity.getName());
        System.out.println("count = " + count);
        isTrue(0 == count, "should be exactly ZERO Company");
    }

}