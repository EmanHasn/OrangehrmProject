package locators;

import org.openqa.selenium.By;

public class DynamicLocators {
    //AdminEnableLocator
    public By getAdminEnableLocator(String label) {
        return By.xpath("//div[@class=\"oxd-select-option\"] [contains(.,'" + label + "')]");
    }
    public By getUserNamePasswordEmployeeLocator(String label) {
        return By.xpath("(//label[contains(.,'" + label + "')]//following::input)[1]");
    }
    public By getRoleAndStatusLocator(String label) {

        return By.xpath("//label[contains(.,'" + label + "')]/../following-sibling::div");
    }



}
