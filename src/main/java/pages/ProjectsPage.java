package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProjectsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public ProjectsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public ProjectsListPage goToProjectsPage(){
        WebElement projects = driver.findElement(By.xpath("//div[@data-i18n='Projects']"));
        projects.click();

        return new ProjectsListPage(driver);
    }
}
