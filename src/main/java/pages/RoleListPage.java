package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class RoleListPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public RoleListPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void creatingNewRole(String roleName) {
        // Click "Add New Role" button
        WebElement newRole = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space()='Add New Role']")));
        newRole.click();

        // Enter role name
        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("modalRoleName")));
        nameField.sendKeys(roleName);

        // Click "Submit" button
        WebElement submit = driver.findElement(By.xpath(
                "//div[@class='col-sm-6 mb-sm-0 mb-3']//button[@type='submit'][normalize-space()='Submit']"));
        submit.click();
    }

    public EditRolePage editRole(){
        WebElement editRole = driver.findElement(By.xpath("//div[3]//div[1]//div[1]//div[2]//a[1]//i[1]"));
        editRole.click();
        return new EditRoleOage (driver);
    }
}