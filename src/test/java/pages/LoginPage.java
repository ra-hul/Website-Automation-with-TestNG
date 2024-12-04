package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPage {

    @FindBy(name = "username")
    public WebElement txtUsername;

    @FindBy(name = "password")
    public WebElement txtPassword;

    @FindBy(css = "[type = submit]")
    public WebElement btnSubmit;

    @FindBy(className = "oxd-userdropdown-img")
    public WebElement btnProfileImage;

    @FindBy(css = "[role=menuitem]")
    public List <WebElement> dropdownMenu;



    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public  void doLogin(String username, String password){
        txtUsername.sendKeys(username);
        txtPassword.sendKeys(password);
        btnSubmit.click();
    }

    public void doLogout(){
        btnProfileImage.click();
        dropdownMenu.get(3).click();
    }
}
