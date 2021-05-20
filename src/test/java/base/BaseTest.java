package base;

import com.google.common.io.Files;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.CountryPage;
import utils.EventReporter;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    private EventFiringWebDriver driver;
    protected CountryPage countryPage;

    @BeforeClass
    public void setUpRemote() throws MalformedURLException {
        Capabilities chromeOptions = DesiredCapabilities.chrome();
        driver = new EventFiringWebDriver(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeOptions));
        driver.register(new EventReporter());
        goMain();
    }

//    @BeforeClass
//    public void setUp(){
//        System.setProperty("webdriver.chrome.driver", "src/chromedriver");
//        driver = new EventFiringWebDriver(new ChromeDriver());
//        driver.register(new EventReporter());
//        goMain();
//    }

    @BeforeMethod
    public void goMain(){
        driver.manage().deleteAllCookies();
        driver.get("https://www.bestbuy.com");
        countryPage = new CountryPage(driver);
    }

    @AfterMethod
    public void recordFailure(ITestResult result){
        if(ITestResult.FAILURE == result.getStatus())
        {
            var camera = (TakesScreenshot)driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            try{
                Files.move(screenshot, new File("resources/screenshots/" + result.getName() + ".png"));
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

}