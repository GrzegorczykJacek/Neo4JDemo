package com.jacek.grzegorczyk.neo4jdemo.services;

import com.jacek.grzegorczyk.neo4jdemo.data.entities.CarEntity;
import com.jacek.grzegorczyk.neo4jdemo.data.repositories.CarRepository;
import com.jacek.grzegorczyk.neo4jdemo.services.dto.CarDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@Transactional
@RequiredArgsConstructor
public class CarEntityServiceImpl implements CarEntityService {

    private final CarRepository carRepository;

    @Override
    public CarDTO create(CarDTO carDTO) {
        CarEntity carEntity = new CarEntity();
        copyProperties(carDTO, carEntity);
        CarEntity saved = carRepository.save(carEntity);

        CarDTO savedCarDTO = new CarDTO();
        copyProperties(saved, savedCarDTO);

        return savedCarDTO;
    }

    @Override
    public List<CarDTO> findByBrandAndOwner(String brand, String owner) {
        List<CarEntity> found = carRepository.findCarEntitiesByBrandAndOwnerThatStartsWith(brand, owner);

        return found.stream()
                .map(carEntity -> {
                    CarDTO carDTO = new CarDTO();
                    copyProperties(found, carDTO);
                    return carDTO;
                }).collect(toList());
    }

}
