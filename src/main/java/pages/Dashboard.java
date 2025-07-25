package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Dashboard {
    private WebDriver driver;
    private WebDriverWait wait;

    public Dashboard(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By goToProductPage = By.xpath("//div[@data-i18n='Product Management']");
    private By goToProducts = By.xpath("//li[@class='menu-item open']//div[@data-i18n='Products']");
    private By orderManagement = By.xpath("//div[@data-i18n='Order Management']");

    public ProductPage clickGoToProductPage() {
        WebElement pm = wait.until(ExpectedConditions.elementToBeClickable(goToProductPage));
        pm.click();

        WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(goToProducts));
        productLink.click();

        return new ProductPage();
    }

    public CustomerOderPage clickOrderManagement(){
        WebElement om = wait.until(ExpectedConditions.elementToBeClickable(orderManagement));
        om.click();

        return new CustomerOderPage(driver);
    }



}
