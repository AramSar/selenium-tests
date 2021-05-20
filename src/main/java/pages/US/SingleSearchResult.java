package pages.US;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SingleSearchResult {
    private WebDriver driver;
    private WebElement result;
    public Double price;
    public String name;
    public String id;

    public SingleSearchResult(WebDriver driver, WebElement element) {
        this.driver = driver;
        this.result = element;
        this.id = result.getAttribute("data-sku-id");

        String price_content = this.result.findElement(By.className("sku-list-item-price")).getText();

        Pattern pattern = Pattern.compile("(?<=Your price for this item is \\$).*\n");
        Matcher matcher = pattern.matcher(price_content);

        if(matcher.find()){
            this.price = Double.parseDouble(matcher.group());
        };

        this.name = result.findElement(By.className("sku-header")).getText();
    }

    public void addToCart(int i){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        By by = By.className("fulfillment-add-to-cart-button");
        ArrayList<WebElement> w = (ArrayList<WebElement>) driver.findElements(by);
        js.executeScript("arguments[0].scrollIntoView();", w.get(i));
        w.get(i).click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("success")));
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("added-to-cart")));
    }

    public String getAddedText(){
        return driver.findElement(By.className("added-to-cart")).getText();
    }

    public void closeCartAddingPage(){
        driver.findElement(By.cssSelector(".btn-default-link.close-modal-x")).click();
    }

    public void save(int i) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        By by = By.className("save-for-later-save");
        ArrayList<WebElement> w = (ArrayList<WebElement>) driver.findElements(by);
        js.executeScript("arguments[0].scrollIntoView();", w.get(i));
        w.get(i).click();
        Thread.sleep(2000);
//        new WebDriverWait(driver, 2).until(ExpectedConditions.textToBePresentInElement(w.get(i),"Saved"));
    }

    public String getSaveText(){
        return result.findElement(By.className("save-for-later-save")).getText();
    }
}
