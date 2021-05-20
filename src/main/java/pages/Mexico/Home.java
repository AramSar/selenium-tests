package pages.Mexico;
import org.openqa.selenium.WebDriver;

public class Home implements pages.Home{
    private WebDriver driver;
    public String url;

    public Home(WebDriver driver) {
        this.driver = driver;
        this.url = driver.getCurrentUrl();
    }
}
