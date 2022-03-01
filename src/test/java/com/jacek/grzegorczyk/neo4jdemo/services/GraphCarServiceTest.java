package com.jacek.grzegorczyk.neo4jdemo.services;

import com.jacek.grzegorczyk.neo4jdemo.AbstractIntegrationTest;
import com.jacek.grzegorczyk.neo4jdemo.data.entities.CarEntity;
import com.jacek.grzegorczyk.neo4jdemo.data.entities.CompanyEntity;
import com.jacek.grzegorczyk.neo4jdemo.data.repositories.CarRepository;
import com.jacek.grzegorczyk.neo4jdemo.services.dto.CarDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.util.Assert.isTrue;
import static org.springframework.util.Assert.notNull;

class GraphCarServiceTest extends AbstractIntegrationTest {
    public static final Supplier<Void> EXCEPTION_SUPPLIER = () -> {
        throw new RuntimeException("this should fail");
    };

    @Autowired
    CarEntityServiceImpl carEntityService;

    @Autowired
    CarRepository carRepository;

    @Test
    void shouldSaveNewCarToNeo4J() {
        // Given
        CarDTO carDTO = new CarDTO();
        carDTO.setBrand(FAKER.funnyName().name());

        HashMap<String, Object> properties = new HashMap<>();
        properties.put(FAKER.funnyName().name(), FAKER.number().randomDigit());
        properties.put(FAKER.funnyName().name(), FAKER.bool().bool());
        properties.put(FAKER.funnyName().name(), FAKER.funnyName().name());
        carDTO.setProperties(properties);

        // When
        CarDTO createdCarDTO = carEntityService.create(carDTO);

        // Then
        notNull(createdCarDTO, "should be created");
        notNull(createdCarDTO.getId(), "should have an id");

        isTrue(1 == carRepository.countByBrand(createdCarDTO.getBrand()), "should be exactly one Company");
    }

    @Test
    void shouldReturnCarsByBrandAndOwner() {
        // Given
        String brand = "Porsche";
        String ownerStartsWith = "Hen";

        // When
        List<CarEntity> results = carRepository.findCarEntitiesByBrandAndOwnerThatStartsWith(brand, ownerStartsWith);

        // Then
        isTrue(1 == results.size(), "should be exactly one result");
        notNull(results.get(0).getProperties().get("year"), "should have property year");
        notNull(results.get(0).getProperties().get("vin"), "should have property vin");
    }
}