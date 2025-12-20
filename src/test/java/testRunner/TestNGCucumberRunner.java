package testRunner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

    @CucumberOptions(
            features ={ "src\\test\\java\\features"},
            glue ={"stepDefinitions"},
            plugin = {"pretty","json:target/cucumber-report/cucumber.json",
                    "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:","rerun:target/rerun.txt"}
    )
    public class TestNGCucumberRunner extends  AbstractTestNGCucumberTests {

        @Override
        @DataProvider(parallel = true)
        public Object[][] scenarios() {
            return super.scenarios();
        }
}
