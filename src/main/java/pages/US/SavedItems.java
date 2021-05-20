package pages.US;

import jdk.jfr.Threshold;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class SavedItems {
    private WebDriver driver;
    public ArrayList<String> namesOfElements = new ArrayList<>();

    public SavedItems(WebDriver driver) throws InterruptedException {
        this.driver = driver;
        Thread.sleep(3000);
        for(WebElement e: driver.findElements(By.className("card-title"))){
            String name = e.getText();
            namesOfElements.add(name);
        }
    }
}
