package App;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;

public class P02_profilePage {
    SHAFT.GUI.WebDriver driver;
    public P02_profilePage(SHAFT.GUI.WebDriver driver){
        this.driver=driver;

    }
    By Admin = By.xpath("//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name' and contains(.,'Admin')]");
    public P03_RecordsPage ClickAdminTab(){
        driver.element().click(Admin);
        return new P03_RecordsPage(driver);
    }

}
