package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CountryPage {

        private WebDriver driver;

        public CountryPage(WebDriver driver){
            this.driver = driver;
        }

        private void selectCountry(String country){
            WebElement region = driver.findElement(
                    By.cssSelector(String.format("img[alt='%s']", country))
            );
            region.click();
        }

        public pages.US.ModalPage selectUS(){
            selectCountry("United States");
            By modalBy = By.xpath("//*[@id=\"widgets-view-email-modal-mount\"]/div/div/div[1]/div");
            try {
                new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(modalBy));
            } catch (TimeoutException e) {

            }
            return new pages.US.ModalPage(driver);
        }
        public pages.Mexico.Home selectMexico(){
            selectCountry("Mexico");
            return new pages.Mexico.Home(driver);
        }
        public pages.Canada.Home selectCanada(){
            selectCountry("Canada");
            return new pages.Canada.Home(driver);
        }
}
