package pages.US;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class Home implements pages.Home {
    private WebDriver driver;

    public Home(WebDriver driver) {
        this.driver = driver;
    }

    public Cart clickCart() throws InterruptedException {
        driver.findElement(By.className("cart-link")).click();
        return new Cart(driver);
    }

    public SavedItemsDd clickSavedItems() throws InterruptedException {
        WebElement w = driver.findElements(By.className("bottom-nav-menu-item")).get(3);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", w);
        w.click();
        return new SavedItemsDd(driver);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void addSearchQuery(String query) throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.moveToElement(locateSearchBar());
        actions.click();
        Thread.sleep(2000);
        actions.click();
        actions.sendKeys(query);
        actions.build().perform();
//        locateSearchBar().sendKeys(query);
//        ((JavascriptExecutor)driver).executeScript("arguments[0].setAttribute('value', arguments[1])",locateSearchBar(), query);
    }

    public String getQuery(){
        return locateSearchBar().getAttribute("value");
    }

    public SearchResults enterSearch(){
        Actions actions = new Actions(driver);
        actions.moveToElement(locateSearchBar());
        actions.click();
        actions.sendKeys(Keys.ENTER);
        actions.build().perform();
//        locateSearchBar().sendKeys(Keys.ENTER);
        return new SearchResults(driver);
    }

    public SearchResults clickSearchButton(){
//        ((JavascriptExecutor)driver).executeScript("arguments[0].click()",driver.findElement(By.className("header-search-button")));
        driver.findElement(By.className("header-search-button")).click();
//        new WebDriverWait(driver, 5).until(
//                ExpectedConditions.visibilityOfElementLocated(By.className("shop-search-header")));
        return new SearchResults(driver);
    }

    private WebElement locateSearchBar(){
        return driver.findElement(By.id("gh-search-input"));
    }
}
