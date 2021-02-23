package com.itmo.backend.database.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Scanner;

@Configuration
@EnableTransactionManagement
public class DBConfig {

    @Value("${db_uri}")
    private String API_KEY;

    @Bean
    public BasicDataSource dataSource() throws URISyntaxException, IOException {
        Optional<String> uri = Optional.ofNullable(System.getenv("DATABASE_URL"));
        URI dbUri = new URI(uri.orElseGet(() -> API_KEY));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

        return basicDataSource;
    }
}
