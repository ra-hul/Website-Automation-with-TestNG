package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MyInfoPage {

    @FindBy(className = "oxd-main-menu-item")
    public List<WebElement> leftMenuBar;

    @FindBy(xpath = "//span[contains(@class, 'oxd-radio-input')]")
    public List<WebElement> radioMaleFeMale;

    @FindBy(xpath = "//input[contains(@value, \"1\")]")
    public WebElement radioBtnMale;

    @FindBy(xpath = "//input[contains(@value, \"2\")]")
    public WebElement radioBtnFemale;

    @FindBy(className = "oxd-select-text-input")
    public List<WebElement> dropdownBloodType;

    @FindBy(css = "[loading=false]")
    public List<WebElement> bloodList;

    @FindBy(className = "orangehrm-left-space")
    public List<WebElement> btnSave;

    public MyInfoPage(WebDriver driver){
        PageFactory.initElements(driver,this);

    }


}
