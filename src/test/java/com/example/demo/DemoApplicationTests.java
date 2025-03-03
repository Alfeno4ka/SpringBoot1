package com.example.demo;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {

    @Autowired
    TestRestTemplate restTemplate;

    private static GenericContainer devApp;
    private static GenericContainer prodApp;

    @BeforeAll
    public static void setUp() {
        devApp = new GenericContainer("springapp8080")
                .withExposedPorts(8080)
                .withEnv("SERVER_PORT", "8080")
                .withEnv("IS_DEV", "true");
        devApp.start();

        prodApp = new GenericContainer("springapp8081")
                .withExposedPorts(8081)
                .withEnv("SERVER_PORT", "8081")
                .withEnv("IS_DEV", "false");
        prodApp.start();
    }

    @Test
    void devAppTest() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:" + devApp.getMappedPort(8080) + "/profile", String.class);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(responseEntity.getBody(), "Current profile is dev");
    }

    @Test
    void prodAppTest() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:" + prodApp.getMappedPort(8081) + "/profile", String.class);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(responseEntity.getBody(), "Current profile is production");
    }
}