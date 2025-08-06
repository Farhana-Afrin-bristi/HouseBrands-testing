package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EditRolePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public void EditRolePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public void dashboardPermissions(){
        
    }
}
