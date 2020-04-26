package com.pma.app.controllers;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.*;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Elimane on Apr, 2020, at 23:11
 */
//For integration test we don't care about the port during test process
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
class HttpRequestTest {
    @LocalServerPort
    private int port;

    //Template made for integration tests
    @Autowired
    private TestRestTemplate testTemplate;

    @Test
    public void getApllicationNameFromHomePage()
    {
        String renderHtml = this.testTemplate.getForObject("http://localhost:"+ port +"/", String.class);
        assertEquals(renderHtml.contains("PMATest"),true);
    }

    @Test
    void displayProjectForm() {
    }

    @Test
    void createProject() {
    }

    @Test
    void displayProjects() {
    }
}
