package runner;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/cucumber.html", "json:target/cucumber.json"},
        glue = "StepDefs",
        features = "src/main/resources",
        tags = "@Test"
)
public class TestRunner {
}