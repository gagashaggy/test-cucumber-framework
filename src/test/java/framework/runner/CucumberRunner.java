package framework.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        glue = {"framework/steps"},
        features = {"src/test/resources/features"},
        tags = "@test"
)
public class CucumberRunner {}
