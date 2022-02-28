package com.jacek.grzegorczyk.neo4jdemo.data.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String s) {
        super(s);
    }
}
