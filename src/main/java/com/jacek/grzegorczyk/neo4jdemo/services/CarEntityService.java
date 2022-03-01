package com.jacek.grzegorczyk.neo4jdemo.services;

import com.jacek.grzegorczyk.neo4jdemo.services.dto.CarDTO;

import java.util.List;

public interface CarEntityService {

    CarDTO create(CarDTO carDTO);

    List<CarDTO> findByBrandAndOwner(String brand, String owner);
}
