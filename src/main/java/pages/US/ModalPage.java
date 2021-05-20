package pages.US;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ModalPage {

    private WebDriver driver;
    public String url;

    public ModalPage(WebDriver driver){
        this.driver = driver;
        this.url = driver.getCurrentUrl();
    }

    public Home closeModal(){
        try {
            WebElement closeMark = driver.findElement(By.xpath("//*[@id=\"widgets-view-email-modal-mount\"]/div/div/div[1]/div"));
            new WebDriverWait(driver, 2);
            closeMark.click();
            return new Home(driver);
        } catch (Exception e) {
            return new Home(driver);
        }
    }

    public void inputEmail(String email){
        driver.findElement(By.xpath("//*[@id=\"input-894e7898-2f61-440e-9555-de57c05b19f1\"]")).sendKeys(email);
    }

    public String getEmailInput(){
        return driver.findElement(By.xpath("//*[@id=\"input-894e7898-2f61-440e-9555-de57c05b19f1\"]")).getAttribute("value");
    }

    public void clickEmailSignUp(){
        driver.findElement(By.xpath("//*[@id=\"marketingCommunicationId-894e7898-2f61-440e-9555-de57c05b19f1\"]/div/div[2]/div/input")).click();
    }

    public boolean successVisibility(){
        return driver.findElement(By.xpath("//*[@id=\"marketingCommunicationId-894e7898-2f61-440e-9555-de57c05b19f1\"]/div/div/div[2]")).isDisplayed();
    }

    public String getEmailSectionText(){
        return driver.findElement(By.xpath("//*[@id=\"marketingCommunicationId-894e7898-2f61-440e-9555-de57c05b19f1\"]/div/div/div[2]/span")).getText();
    }

    public boolean isVisible(){
        return driver.findElement(By.xpath("//*[@id=\"widgets-view-email-modal-mount\"]/div/div/div[1]/div")).isDisplayed();
    }

}

