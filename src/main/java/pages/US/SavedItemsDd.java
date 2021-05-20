package pages.US;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class SavedItemsDd {
    private static WebDriver driver;
    public ArrayList<String> namesOfSavedItems = new ArrayList<>();

    public SavedItemsDd(WebDriver driver) throws InterruptedException {
        this.driver = driver;
        Thread.sleep(2000);
        for(WebElement e: driver.findElements(By.className("item"))){
            String name = e.findElement(By.className("sku-card-product-title")).getText();
            namesOfSavedItems.add(name);
        }
    }

    public SavedItems seeAllList() throws InterruptedException {
        driver.findElement(By.className("see-all-link")).click();
        return new SavedItems(driver);
    }
}
