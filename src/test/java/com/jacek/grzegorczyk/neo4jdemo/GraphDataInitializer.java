package com.jacek.grzegorczyk.neo4jdemo;

import lombok.SneakyThrows;
import org.liquigraph.core.api.Liquigraph;
import org.liquigraph.core.configuration.Configuration;
import org.liquigraph.core.configuration.ConfigurationBuilder;
import org.neo4j.jdbc.bolt.BoltNeo4jDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Component
public class GraphDataInitializer implements ApplicationRunner {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${neo4j.clear.before.migrations}")
    Boolean clear;
    @Value("${neo4j.port}")
    Integer port;
    @Value("${neo4j.server-name}")
    String serverName;
    @Value("${spring.neo4j.authentication.username}")
    String username;
    @Value("${spring.neo4j.authentication.password}")
    String password;
    @Value("${liquibase.location}")
    String location;

    DataSource dataSource;

    @Override
    public void run(ApplicationArguments args) {
        dataSource = dataSource();

        if (clear) {
            clearDatabase(dataSource);
            logger.info("Cleared Neo4J database");
        }

        var liquigraph = new Liquigraph();

        liquigraph.runMigrations(configuration(this.dataSource));

        logger.info("Executed Neo4J migrations");
    }

    Configuration configuration(DataSource dataSource) {
        return new ConfigurationBuilder()
                .withDataSource(dataSource)
                .withMasterChangelogLocation(location)
                .withRunMode()
                .build();
    }

    private BoltNeo4jDataSource dataSource() {
        var dataSource = new BoltNeo4jDataSource();

        dataSource.setPassword(password);
        dataSource.setUser(username);
        dataSource.setServerName(serverName);
        dataSource.setPortNumber(port);
        return dataSource;
    }

    @SneakyThrows
    static void clearDatabase(DataSource dataSource) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement stmt = con.prepareStatement("MATCH (n) DETACH DELETE n");
             ResultSet rs = stmt.executeQuery()) {
        }
    }
}
