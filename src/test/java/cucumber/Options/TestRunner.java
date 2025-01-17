package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features/PlaceValidations.feature",
					glue = {"stepDefinitions"},
					tags = "@createLocation",
					plugin = "json:target/jsonReports/cucumber-report.json")
public class TestRunner {
	
}
