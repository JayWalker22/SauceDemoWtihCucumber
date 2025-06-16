package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepDefinitions","hooks"},
        tags="@smoke",
        plugin = {
                "pretty",                               // Konsola renkli detaylı çıktı
                "html:target/cucumber-reports.html",   // HTML rapor
                "json:target/cucumber.json",            // JSON rapor
                "junit:target/cucumber.xml",
                "json:target/json-reports/cucumber-report.json",// JUnit XML rapor
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" // ExtentReports (opsiyonel)
        },
        monochrome = true                           // Konsol çıktısını okunabilir yapar
)
public class TestRunner {
}