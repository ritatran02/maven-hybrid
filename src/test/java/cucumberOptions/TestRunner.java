package cucumberOptions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/features",
        glue = "stepDefinitions",
        monochrome = true,
        plugin = {
                "pretty",
                "html:target/site/cucumber-report-default",
                "json:target/site/cucumber.json"
        },
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        tags = "@displayed"
)
public class TestRunner extends AbstractTestNGCucumberTests {
}