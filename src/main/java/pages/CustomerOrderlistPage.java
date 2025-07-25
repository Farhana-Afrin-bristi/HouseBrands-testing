package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CustomerOrderlistPage {

    private WebDriver driver;
    private WebDriverWait wait;
    public CustomerOrderlistPage(WebDriver driver) {

            this.driver = driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            }

            public CreateOrderPage CreateOrderButton() {
            WebElement createorderButton = driver.findElement(By.xpath("//span[contains(text(),'Create Order')]"));
                createorderButton.click();

            return new CreateOrderPage(driver);

        }
        }


