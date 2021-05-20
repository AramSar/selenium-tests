package pages.US;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class Cart {
    private WebDriver driver;
    public ArrayList<SingleCartElement> cartElements = new ArrayList<>();
    public double totalPrice;

    public Cart(WebDriver driver) throws InterruptedException {
        this.driver = driver;
        Thread.sleep(2000);
        for(WebElement elem: this.driver.findElements(By.className("fluid-item"))){
            SingleCartElement s = new SingleCartElement(driver, elem);
            this.cartElements.add(s);
        }
        this.totalPrice = this.getTotalPrice();
    }

    public static boolean isBadgeVisible(WebDriver driver){
        try {
            WebElement w = driver.findElement(By.className("dot"));
            return w.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public static int getCartBadge(WebDriver driver) throws InterruptedException {
        Thread.sleep(2000);
        WebElement w = driver.findElement(By.className("dot"));
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(w));
        return Integer.parseInt(w.getText());
    }

    public double getTotalPrice() throws InterruptedException {
        Thread.sleep(2000);
        return Double.parseDouble(
                this.driver.findElement(By.className("price-summary__total-value")).getText().substring(1)
        );
    }

    public void reomveElementFromCart(SingleCartElement e) throws InterruptedException {
        e.remove();
        updateListandPrice();
    }

    public String getRemovalText() {
        new WebDriverWait(driver, 2).
                until(ExpectedConditions.visibilityOf(driver.findElement(By.className("removed-item-info"))));
        return driver.findElement(By.cssSelector(".removed-item-info__message.col-xs-6")).getText();
    }

    public void addAmount(SingleCartElement e, int amount) throws InterruptedException {
        e.addAmount(amount);
        this.totalPrice = this.getTotalPrice();
    }

    private void updateListandPrice() throws InterruptedException {
        WebElement elem = this.driver.findElements(By.className("fluid-item")).get(0);
        SingleCartElement s = new SingleCartElement(driver, elem);
        this.cartElements.add(s);
        this.totalPrice = this.getTotalPrice();
    }

}
