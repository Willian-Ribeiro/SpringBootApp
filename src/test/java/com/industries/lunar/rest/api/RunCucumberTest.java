package com.industries.lunar.rest.api;

import com.industries.lunar.rest.RestApplication;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;

@RunWith(Cucumber.class)
@CucumberContextConfiguration
@CucumberOptions(
        features = "src/test/resources/features",
        plugin = {"pretty", "html:target/reports/api.html"},
        glue = {"com.industries.lunar.rest.api"}
)
@SpringBootTest(
        classes = RestApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
@AutoConfigureWebTestClient
public class RunCucumberTest {}
