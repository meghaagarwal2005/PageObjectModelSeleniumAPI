package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"target/parallel/features/[CUCABLE:FEATURE].feature"},
		plugin = {"json:target/cucumber-report/[CUCABLE:RUNNER].json"},
		dryRun=false,
		monochrome=false,
		tags = "not @ignore and @primary"
)

public class CucableTemplate {
}
