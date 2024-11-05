package App;

import com.shaft.driver.SHAFT;
import locators.DynamicLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class P03_RecordsPage {
    SHAFT.GUI.WebDriver driver;
    public P03_RecordsPage(SHAFT.GUI.WebDriver driver){
        this.driver=driver;
    }
    //locators
    By RecordSpan = By.xpath("//div/span[@class='oxd-text oxd-text--span']");
    By addBtn = By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary' and contains(.,'Add')]");
    By SuccssBox = By.xpath("//p[@class='oxd-text oxd-text--p oxd-text--toast-title oxd-toast-content-text']");
    //locator for search step
    By searchUsernameInput = By.xpath("//label[@class='oxd-label' and contains(.,'Username')]//parent::div[1]//following::div[1]//input[1]");
    By selectEmpolyee = By.xpath("//div[@class='oxd-autocomplete-dropdown --positon-bottom']//div[1]//span[1]");
    By emolyeeNameEle = By.xpath("//span[@class=\"oxd-userdropdown-tab\"]//p");
    By searchBtn = By.xpath("//button[@type='submit' and contains(.,' Search ')]");
    //locators for delete function
    By delBtn = By.xpath("//div[@role='table']//div[@class='oxd-table-body']//div[1]//div[@class='oxd-table-row oxd-table-row--with-border']//button[1]//i[@class='oxd-icon bi-trash']");
    By yesDelBtn = By.xpath("//i[@class='oxd-icon bi-trash oxd-button-icon']/parent::button[contains(.,' Yes, Delete ')]");
    By delMSG = By.xpath("//p[@class='oxd-text oxd-text--p oxd-text--toast-message oxd-toast-content-text' and contains(.,'Successfully Deleted')]");
    By resetBtn = By.xpath("//div[@class='oxd-form-actions']//button[contains(.,' Reset ')]");
    //dynamic Locators
    DynamicLocators dynamicLocators = new DynamicLocators();

    String recordstr;
    int numberOnly;
    static int OriginalNoOfRecord;
    static int AfterAddNewUser;
    static int AfterDeleteUser;
    public int getRecordNo()
    {
        recordstr= driver.element().getText(RecordSpan).replaceAll("[^0-9]", "");
        numberOnly= Integer.valueOf( recordstr );
        System.out.println(numberOnly);
        return numberOnly;
    }
    public P03_RecordsPage OriginalRecordNo()
    {
        OriginalNoOfRecord=getRecordNo();
        System.out.println("the original number of records: " + OriginalNoOfRecord);
        return this;
    }
    public P04_AddFormPage Addbtn(){
        System.out.println("\n data type is "+  ((Object)numberOnly).getClass().getSimpleName()  );
        driver.element().click(addBtn);
        return new P04_AddFormPage(driver);
    }
    public P03_RecordsPage Sucess()
    {
        driver.element().assertThat(SuccssBox).exists().perform();

        return new P03_RecordsPage(driver);
    }
    public P03_RecordsPage AfterAddUserRecordNo()
    {
        AfterAddNewUser=getRecordNo();
        System.out.println("Recorder number After Adding new User: " + AfterAddNewUser);
        return this;
    }
    public P03_RecordsPage verifyRecordNumber()
    {
        SHAFT.Validations.verifyThat().object(AfterAddNewUser).isEqualTo((OriginalNoOfRecord+1)).perform();
        return this;
    }
    public String getEmpolyeeName(){
        return driver.element().getText(emolyeeNameEle);
    }
    public P03_RecordsPage SearchForUser(String searchDataUserName) throws InterruptedException {
        driver.element().type(searchUsernameInput,searchDataUserName);

        driver.element().click(dynamicLocators.getRoleAndStatusLocator("User Role")).
                and().click(dynamicLocators.getAdminEnableLocator("Admin"));
        driver.element().type(dynamicLocators.getUserNamePasswordEmployeeLocator("Employee Name"),getEmpolyeeName());

        WebDriverWait wait = new WebDriverWait(driver.getDriver(), Duration.ofSeconds(5));
        wait.until(d -> driver.element().waitUntilPresenceOfAllElementsLocatedBy(selectEmpolyee));
        driver.element().click(selectEmpolyee);

        driver.element().click(dynamicLocators.getRoleAndStatusLocator("Status"))
                .click(dynamicLocators.getAdminEnableLocator("Enabled"));
        driver.element().click(searchBtn);
        return this;

    }

    public P03_RecordsPage deleteUser() throws InterruptedException {
        driver.element().click(delBtn).and().click(yesDelBtn);
        SHAFT.Validations.verifyThat().object(driver.element().getText(delMSG)).isEqualTo("Successfully Deleted").perform();
        return this;
    }
    public P03_RecordsPage verifyRecordNumbersAfterDeleteUser()
    {
        driver.element().click(resetBtn);
        AfterDeleteUser = getRecordNo();
        System.out.println("Records Number after Delete User: " +AfterDeleteUser);
        SHAFT.Validations.verifyThat().object(AfterDeleteUser).isEqualTo((AfterAddNewUser-1)).perform();
        return this;
    }
}
