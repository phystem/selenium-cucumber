package testrunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, plugin = { "pretty",
		"html:target/site/cucumber", "json:target/cucumber/cucumber.json" }, 
		glue = "steps", features = "classpath:features/autoquote.feature")
public class AutoQuoteTest {

}
