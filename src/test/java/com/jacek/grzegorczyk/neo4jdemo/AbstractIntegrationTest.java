package com.jacek.grzegorczyk.neo4jdemo;

import com.github.javafaker.Faker;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(classes = {Neo4JDemoApplication.class}, webEnvironment = RANDOM_PORT)
@ActiveProfiles({"test"})
public abstract class AbstractIntegrationTest {

    @LocalServerPort
    int port;

    protected static Faker FAKER = new Faker();

}
