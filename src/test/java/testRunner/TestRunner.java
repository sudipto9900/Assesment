package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		features=".//Features/Home.feature",
		glue="stepDefinitions",
		dryRun=false,
		monochrome=true,
		tags="@automated",
		plugin= {"pretty","html:test-output"}
		)
public class TestRunner {
	
}
