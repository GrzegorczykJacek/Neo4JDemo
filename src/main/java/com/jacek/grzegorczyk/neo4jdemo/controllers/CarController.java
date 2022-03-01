package com.jacek.grzegorczyk.neo4jdemo.controllers;

import com.jacek.grzegorczyk.neo4jdemo.services.CarEntityService;
import com.jacek.grzegorczyk.neo4jdemo.services.dto.CarDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarEntityService carEntityService;

    @PostMapping
    public CarDTO create(CarDTO carDTO) {
        return carEntityService.create(carDTO);
    }

    @GetMapping
    public List<CarDTO> findByBrandAndOwner(@RequestParam String brand, @RequestParam String owner) {
        return carEntityService.findByBrandAndOwner(brand, owner);
    }

}
