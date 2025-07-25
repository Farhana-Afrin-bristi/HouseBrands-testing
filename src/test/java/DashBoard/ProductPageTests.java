package DashBoard;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

import java.time.Duration;


public class ProductPageTests extends BaseTest {


  private CharSequence expectedMessage;

  public void GoToProductPage(){

      WebDriverWait wait;


      ProductPage productPage = dashboard.clickGoToProductPage();
      productPage.addProduct();

      // Creating new products
        CreateProduct createProduct = new CreateProduct(driver);
        createProduct.addProductTittle("Product 1");
        createProduct.addDescription("It is a long established fact that a reader will be distracted by the readable" +
                " content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a " +
                "more-or-less normal distribution of letters, as opposed to using 'Content here, content here', " +
                "making it look like readable English. Many desktop publishing packages and web page editors now use " +
                "Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web " +
                "sites still in their infancy. Various versions have evolved over the years, " +
                "sometimes by accident, sometimes on purpose (injected humour and the like).");

        String thumbNail = System.getProperty("user.dir") + "/resources/image.jpg";
        createProduct.uploadFiles(thumbNail);
        createProduct.scrollDown();
        createProduct.basePrice(2000);
        createProduct.forceSelectCategoryByValue("4");
        createProduct.scrollDown();
        createProduct.selectUnit( "Set");
        createProduct.submit();
        createProduct.assertProductCreatedWithToast("Product Created");
  }

  public void assertProductCreatedWithToast(String expectedMessage) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector(".toast-message") // Make sure this matches your actual toast selector
    ));

    Assert.assertTrue(toast.getText().contains(expectedMessage),
            "Expected toast message to contain: '" + expectedMessage + "', but got: '" + toast.getText() + "'");
  }






}
