package com.jacek.grzegorczyk.neo4jdemo.services.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class CarDTO {

    private Long id;

    private String brand;

    private Map<String, Object> properties;

}
