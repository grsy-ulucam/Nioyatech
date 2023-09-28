package runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        features = {"src/test/resources/features/api.feature"},
        glue = {"stepdefinitions"},
        plugin = {"pretty"}
)
public class ApiRunner {

}
