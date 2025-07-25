package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class CreateOrderPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public CreateOrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


        public void selectCustomerByIndex(int index){
            WebElement selectCustomer = driver.findElement(By.id("selected_user"));
            Select select = new Select(selectCustomer);
            select.selectByIndex(index);
        }

//    public void clickAddItemButton(){
//        WebElement addItemButton = driver.findElement(By.xpath("//button[@id='addItemButton']"));
//        addItemButton.click();
//    }

//    public void selectProduct() {
//        WebElement products = driver.findElement(
//                By.cssSelector(".select2-selection.select2-selection--single"));
//        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", products);
//    }
public void selectProduct(String productName) throws InterruptedException {
    WebElement addItemButton = driver.findElement(By.xpath("//button[@id='addItemButton']"));
    addItemButton.click();

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // Click the dropdown
    WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("#select2-modalProductSelect-container")));
    dropdown.click();

    // Optional: Wait a moment to visually verify or debug
    Thread.sleep(1000);

    // Properly concatenate productName into XPath string
    String optionXpath = "//li[contains(@class,'select2-results__option') and contains(text(), '" + productName + "')]";

    // Wait for the option and click it
    WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(optionXpath)));
    option.click();
}

    public void submit() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("addItemSubmitButton")));

        WebElement button = driver.findElement(By.id("addItemSubmitButton"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);

        wait.until(ExpectedConditions.elementToBeClickable(button));

        try {
            button.click();
        } catch (ElementClickInterceptedException e) {
            // Fallback to JS click if blocked
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        }
    }

    public void addProductQuantity(int index, int quantity) {
        String xpath = "//input[@id='product-" + index + "-quantity']";
        WebElement addQuantity = driver.findElement(By.xpath(xpath));
        addQuantity.clear(); // optional
        addQuantity.sendKeys(String.valueOf(quantity));
    }

    public void addDiscount(String discountType, int amount){
        WebElement addDiscountType = driver.findElement(By.xpath("//select[@id='discount_type']"));
        Select select = new Select(addDiscountType);
        select.selectByVisibleText(discountType);
        WebElement addAmount = driver.findElement(By.xpath("//input[@id='discount_value']"));
        addAmount.sendKeys(String.valueOf(amount));
    }

    public void addShippingCharge(int amount){
        WebElement shippingCharge= driver.findElement(By.id("shipping_charge"));
        shippingCharge.sendKeys(String.valueOf(amount));
    }

    public void addShippingAddress(int value){
        String xpath = "//input[@id='shippingAddress" + value + "']";
        WebElement address = driver.findElement(By.xpath(xpath));
        address.click();
    }

   public void createOrder(){
        WebElement createButton = driver.findElement(By.id("create_btn"));
        createButton.click();
   }

    public void assertOrderCreatedWithToast(String expectedMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(@class, 'toast') and contains(., '" + expectedMessage + "')]")
            ));

            String actualText = toast.getText().trim();
            Assert.assertTrue(actualText.contains(expectedMessage),
                    "Expected toast message to contain: '" + expectedMessage + "', but got: '" + actualText + "'");
        } catch (TimeoutException e) {
            Assert.fail("Toast message with expected text '" + expectedMessage + "' was not visible within timeout.");
        }
    }

}
