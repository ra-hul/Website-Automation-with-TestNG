package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DirectoryPage {

    @FindBy(className = "oxd-main-menu-item")
    public List<WebElement> leftMenuBar;

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    public WebElement empSearchbar;

    public DirectoryPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void searchEmpName(String name){
        empSearchbar.sendKeys(name);
    }
}
