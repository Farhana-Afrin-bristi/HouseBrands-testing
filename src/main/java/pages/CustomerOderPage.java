package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CustomerOderPage {
    private WebDriver driver;
    private WebDriverWait wait;


    public CustomerOderPage(WebDriver driver){
        super();
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }


    public CustomerOrderlistPage GoTocustomerOrderListPage() {
        WebElement customerOrderLink = driver.findElement(By.xpath("//div[@data-i18n='Customer Order']/ancestor::a"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", customerOrderLink);

        WebElement orderList = driver.findElement(By.xpath("//a[@href='https://hq.housebrands.com/order']//div[@data-i18n='Order List'][normalize-space()='Order List']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", orderList);

        return new CustomerOrderlistPage(driver);
    }



}
