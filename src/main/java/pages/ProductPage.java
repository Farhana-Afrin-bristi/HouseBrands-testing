package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait;


    public ProductPage() {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By addProduct = By.xpath("//span[contains(text(),'Add Products')]");


    public CreateProduct addProduct(){
        driver.findElement(addProduct).click();
        return new CreateProduct(driver);

    }

}
