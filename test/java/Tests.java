import App.P01_Login;
import App.P02_profilePage;
import App.P03_RecordsPage;
import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class Tests {
    SHAFT.GUI.WebDriver driver;
    SHAFT.TestData.JSON testData;

    @BeforeTest
    public void setDriver() {
        driver = new SHAFT.GUI.WebDriver();
        testData = new SHAFT.TestData.JSON("testDataFile.json");
        driver.browser().navigateToURL("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }
    @Test
    public void login() throws InterruptedException {
       new P01_Login(driver).loginSteps(testData.getTestData("username"),testData.getTestData("password"))
               .ClickAdminTab()
               .OriginalRecordNo()
               .Addbtn()
               .fillForm(testData.getTestData("NewUserName"),testData.getTestData("NewPassword"))
               .Sucess()
               .AfterAddUserRecordNo()
               .verifyRecordNumber()
               .SearchForUser(testData.getTestData("NewUserName"))
               .deleteUser()
               .verifyRecordNumbersAfterDeleteUser();
    }
    @AfterTest
    public void closeUrl()
    {
        driver.quit();
    }
}
