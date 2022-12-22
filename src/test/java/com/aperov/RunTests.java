package com.aperov;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Copyright (c) 2022.
 * cucumber runner class
 *
 * @author Aleksei Perov
 * @version 1.0
 * @since 1.0
 */
@CucumberOptions(
        features = "src/test/java/features",
        plugin = {"pretty", "html:target/cucumber/report.html"},
        glue = {"com.aperov.teststeps"}
//        ,tags = "@UI"
)
@RunWith(Cucumber.class)
public class RunTests {
}
