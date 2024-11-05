package App;
import com.shaft.driver.SHAFT;
import locators.DynamicLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class P04_AddFormPage {
    SHAFT.GUI.WebDriver driver;
    public P04_AddFormPage(SHAFT.GUI.WebDriver driver){
        this.driver=driver;
    }
    //Locators
    DynamicLocators dynamicLocators = new DynamicLocators();
    By emolyeeNameEle = By.xpath("//span[@class=\"oxd-userdropdown-tab\"]//p");
    By selectEmpolyee = By.xpath("(//div[@role=\"option\"])[1]");
    By saveBtn = By.xpath("//button[@type=\"submit\"]");

    //Methods

    public P03_RecordsPage fillForm(String newUserName, String NewPassword) {
        driver.element().click(dynamicLocators.getRoleAndStatusLocator("User Role")).
                and().click(dynamicLocators.getAdminEnableLocator("Admin")).
                and().type(dynamicLocators.getUserNamePasswordEmployeeLocator("Employee Name"),getEmpolyeeName());

        WebDriverWait wait = new WebDriverWait(driver.getDriver(), Duration.ofSeconds(5));
        wait.until(d -> driver.element().waitUntilPresenceOfAllElementsLocatedBy(selectEmpolyee));
        driver.element().click(selectEmpolyee);

                driver.element().click(dynamicLocators.getRoleAndStatusLocator("Status")).click(dynamicLocators.getAdminEnableLocator("Enabled"));

                driver.element().type(dynamicLocators.getUserNamePasswordEmployeeLocator("Username"),newUserName).
                and().type(dynamicLocators.getUserNamePasswordEmployeeLocator("Password"),NewPassword).
                and().type(dynamicLocators.getUserNamePasswordEmployeeLocator("Confirm Password"),NewPassword).
                and().click(saveBtn);
        return new P03_RecordsPage(driver);
    }
    public String getEmpolyeeName(){
        return driver.element().getText(emolyeeNameEle);
    }


}
