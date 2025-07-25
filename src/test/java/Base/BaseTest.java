package Base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pages.Dashboard;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected Dashboard dashboard;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver1.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://hq.housebrands.com/login");

        // Perform login
        driver.findElement(By.id("email")).sendKeys("repev91843@nutrv.com");
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.xpath("//button[contains(text(),'Sign')]")).click();

        dashboard = new Dashboard(driver);

    }

//    @AfterTest
//    public void TearDown(){
//        driver.quit();
//    }


    public void waitForElementVisible(By locator, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}



