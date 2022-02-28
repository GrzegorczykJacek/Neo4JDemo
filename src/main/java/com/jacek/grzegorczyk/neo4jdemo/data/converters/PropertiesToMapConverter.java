package com.jacek.grzegorczyk.neo4jdemo.data.converters;

import org.neo4j.driver.Value;
import org.neo4j.driver.Values;
import org.springframework.data.neo4j.core.convert.Neo4jConversionService;
import org.springframework.data.neo4j.core.convert.Neo4jPersistentPropertyToMapConverter;

import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class PropertiesToMapConverter implements Neo4jPersistentPropertyToMapConverter<String, Object> {
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Value> decompose(Object o, Neo4jConversionService neo4jConversionService) {
        Map<String, Object> prop = (Map<String, Object>) o;

        return prop.entrySet().stream()
                .collect(toMap(Map.Entry::getKey, entry -> Values.value(entry.getValue())));
    }

    @Override
    public Object compose(Map<String, Value> map, Neo4jConversionService neo4jConversionService) {
        return map.entrySet().stream()
                .collect(toMap(Map.Entry::getKey, entry -> entry.getValue().asObject()));
    }
}
