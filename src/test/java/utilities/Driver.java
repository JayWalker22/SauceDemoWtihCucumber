package utilities;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Driver {
    private Driver(){

    }
    static WebDriver driver;
    static DesiredCapabilities capabilities=new DesiredCapabilities();

    public static WebDriver getDriver() {


        if (driver == null) {
            switch (ConfigurationReader.getProperty("browser")) {
                case "chrome":
                    System.setProperty("webdriver.chrome.driver", "C:\\Users\\ayborG\\Desktop\\chromedriver-win64\\chromedriver.exe");
//                    ChromeOptions options= new ChromeOptions();
//                    options.addArguments("--headless"); // Headless test yapmak icin
//                    options.addArguments("--disaple-gpu"); //GPU kullanimini devre disi birakir
//                   driver= new ChromeDriver(options); //bu kodu headless testte acarsinizi

                    //asagidaki kodlar file download yaparken default deger olan download klasoru yerine bir yol vermemize yarar
                    ChromeOptions optionsChrome = new ChromeOptions();
                    optionsChrome.setPageLoadStrategy(PageLoadStrategy.EAGER);
                    optionsChrome.setPageLoadTimeout(Duration.ofSeconds(15));
//                    String filePath ="C:\\Hakan Arsiv";

//                    Map<String, Object> prefs = new HashMap<>();
//                    prefs.put("download.default_directory", filePath);
//                    options.setExperimentalOption("prefs", prefs);
                    //  driver = new ChromeDriver(options);  //bu satirdaki yorum slashlarini bir alt satira indirip calistirmaliyiz.

                    driver = new ChromeDriver(new ChromeOptions().addArguments("--remote-allow-origins=*"));
                    break;

                case "edge":

                    driver = new EdgeDriver();
                    break;

                default:

                    driver = new ChromeDriver(new ChromeOptions().addArguments("--remote-allow-origins=*"));
            }

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }

        return driver;
    }

    public static void closeDriver() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (driver != null) {//Driver'a değer atanmışşsa
            driver.close();
            driver = null;
        }
    }

    public static void quitDriver() {
        if (driver != null) {//Driver'a değer atanmışşsa
            driver.quit();
            driver = null;
        }
    }
}
