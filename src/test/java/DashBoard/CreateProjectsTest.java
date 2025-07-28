package DashBoard;

import Base.BaseTest;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.ProjectsPage;

public class CreateProjectsTest extends BaseTest {
    private CharSequence expectedMessage;

    @Test
    public void goToProjectsPage(){
        WebDriverWait wait;

        ProjectsPage porjects = dashboard.cLickProjectMangement();
        porjects.goToProjectsPage();

    }

}
