package App;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;

public class P01_Login {
    SHAFT.GUI.WebDriver driver;
    public P01_Login(SHAFT.GUI.WebDriver driver){
        this.driver=driver;

    }
    By usernameInp = By.xpath("//input[@name=\"username\"]");
    By passwordInp = By.xpath("//input[@name=\"password\"]");
    By loginBtn = By.xpath("//button[@type=\"submit\"]");
    public P02_profilePage loginSteps(String username, String password){
        driver.element().type(usernameInp,username).
                and().type(passwordInp,password).
                and().click(loginBtn);
    return new P02_profilePage(driver);
    }
}
