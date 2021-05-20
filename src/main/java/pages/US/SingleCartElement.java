package pages.US;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SingleCartElement {
    private WebDriver driver;
    private WebElement element;
    public Double price;
    public String name;
    public int amount = 1;

    public SingleCartElement(WebDriver driver, WebElement element) {
        this.driver = driver;
        this.element = element;
        this.price = Double.parseDouble(this.element.
                findElement(By.className("price-block__primary-price")).
                getText().substring(1));
        this.name = this.element.findElement(By.className("item-title")).getText();
        WebElement elem = this.element.findElement(By.cssSelector(".c-dropdown.v-medium.fluid-item__quantity"));
        Select dropdown = new Select(elem);
        String selectedValue = dropdown.getFirstSelectedOption().getText();
        this.amount = Integer.parseInt(selectedValue);
    }

    protected void remove() throws InterruptedException {
        this.element.findElement(By.cssSelector(".btn-default-link.link-styled-button.cart-item__remove")).click();
        Thread.sleep(2000);
    }

    public void save(int i) throws InterruptedException {
        Thread.sleep(1000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement w = this.element.findElement(By.cssSelector(".btn-default-link.link-styled-button.cart-item__save"));
        js.executeScript("arguments[0].scrollIntoView();", w);
        w.click();
        Thread.sleep(1000);
    }

    public String getSaveText(){
        return driver.findElement(By.cssSelector(".removed-item-info__message.col-xs-6")).getText();
    }

    protected void addAmount(int amount) throws InterruptedException {
        WebElement elem = this.element.findElement(By.cssSelector(".c-dropdown.v-medium.fluid-item__quantity"));
        Select amo = new Select(elem);
        amo.selectByVisibleText(String.valueOf(amount));
        Thread.sleep(2000);
        this.amount = amount;
    }
}
