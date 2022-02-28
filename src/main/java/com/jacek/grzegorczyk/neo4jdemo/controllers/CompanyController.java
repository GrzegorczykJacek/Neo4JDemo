package com.jacek.grzegorczyk.neo4jdemo.controllers;

import com.jacek.grzegorczyk.neo4jdemo.data.entities.CompanyEntity;
import com.jacek.grzegorczyk.neo4jdemo.services.CompanyEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyEntityService companyEntityService;

    @GetMapping
    public CompanyEntity create(CompanyEntity companyEntity) {
        return companyEntityService.create(companyEntity);
    }
}
