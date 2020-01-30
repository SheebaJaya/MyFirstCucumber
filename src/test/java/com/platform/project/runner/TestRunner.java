package com.platform.project.runner;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.platform.project.steps"},
        monochrome = true,
        plugin={"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html","json:target/cucumber-reports/cucumber.json"},
        tags = {"@loginProcess"}
)


public class TestRunner {

}
