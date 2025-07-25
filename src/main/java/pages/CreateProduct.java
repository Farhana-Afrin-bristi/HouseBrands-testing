package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.testng.Assert;
import java.util.List;

import static java.awt.SystemColor.text;

public class CreateProduct {

    private WebDriver driver;
    private WebDriverWait wait;

    private By addProductTittle = By.id("title");
    private By addDescribtions = By.cssSelector("div.ql-editor.ql-blank");


    public CreateProduct (WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void addProductTittle(String tittle){

        driver.findElement(addProductTittle).sendKeys(tittle);
    }

    public void addDescription(String description){

        driver.findElement(addDescribtions).sendKeys(description);
    }


    public void uploadFiles(String filePath) {
    WebElement uploadInput = driver.findElement(By.id("darkLogoInput"));
    uploadInput.sendKeys(filePath);
    }

//    public void uploadMultipleFiles (String... filePaths){
//        WebElement multipleFiles = driver.findElement(By.id("multiple-uploader"));
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated((By) multipleFiles));
//
//        ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='block';", fileInput);
//
//        fileInput.sendKeys(String.join("\n", filePaths));
//   }

    public void basePrice(int price){
        WebElement basePrice = driver.findElement(By.id("product_price"));
        basePrice.sendKeys(String.valueOf(price));
    }

//    public void selectDiscountType(String value) {
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript(
//                "let select = document.getElementById('discout_type');" +
//                        "select.value = '" + value + "';" +
//                        "let event = new Event('change', { bubbles: true });" +
//                        "select.dispatchEvent(event);"
//        );
//    }
    public void scrollDown(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 500);");


    }

    public void forceSelectCategoryByValue(String value) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Set value via JS and trigger change event
        js.executeScript(
                "let select = document.getElementById('category'); " +
                        "select.value = '" + value + "'; " +
                        "const event = new Event('change', { bubbles: true }); " +
                        "select.dispatchEvent(event);"
        );
    }

    public void selectUnit(String optionVisibleText) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            // Open dropdown and select the desired option using Select2 events
            String script =
                    "var select = $('#unit');" +
                            "select.val(select.find('option').filter(function() {" +
                            "  return $(this).text().trim() === '" + optionVisibleText + "';" +
                            "}).val()).trigger('change');";

            js.executeScript(script);

        } catch (Exception e) {
            throw new RuntimeException("❌ Could not select unit '" + optionVisibleText + "' — " + e.getMessage(), e);
        }
    }

    public void submit() {
        WebElement addButton = driver.findElement(By.id("addProduct"));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            // Scroll to the element to make sure it's visible
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", addButton);

            // Wait until it's clickable
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(addButton));

            // Try regular click
            addButton.click();

        } catch (ElementClickInterceptedException e) {
            // Fallback to JS click if blocked
            System.out.println("⚠️ ElementClickInterceptedException – using JS click instead.");
            js.executeScript("arguments[0].click();", addButton);
        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to click Add Product button", e);
        }
    }

    public void assertProductCreatedWithToast(String expectedMessage) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(400));
        WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'Product Created')]")
        ));

        Assert.assertTrue(toast.getText().contains(expectedMessage),
                "Expected toast message to contain: '" + expectedMessage + "', but got: '" + toast.getText() + "'");
    }

}