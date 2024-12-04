package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import setup.EmployeeModel;
import utils.Utils;

import java.util.List;

public class PIMPage {

    @FindBy(className = "oxd-main-menu-item")
    public List<WebElement> leftMenuBar;

    @FindBy(className = "oxd-button")
    public List<WebElement> btnAdd;

    @FindBy(className = "oxd-switch-input")
    public WebElement loginDetailsToggle;

    @FindBy(className = "oxd-input")
    public List <WebElement> txtEmpFields;

    @FindBy(className = "oxd-input--active")
    public List<WebElement> searchEmp;

    @FindBy(css = "[type=submit]")
    public WebElement btnSubmit;



    public PIMPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void createEmp(String firstName, String lastName, String employeeID,String userName, String password, String confirmPass){

    txtEmpFields.get(1).sendKeys(firstName);
    txtEmpFields.get(3).sendKeys(lastName);

    Utils.createEmptyField(txtEmpFields.get(4));
    txtEmpFields.get(4).sendKeys(employeeID);

    txtEmpFields.get(5).sendKeys(userName);
    txtEmpFields.get(6).sendKeys(password);
    txtEmpFields.get(7).sendKeys(confirmPass);

    btnSubmit.click();
    }

    public void createEmp(EmployeeModel model){
        txtEmpFields.get(1).sendKeys(model.getFirstName());
        txtEmpFields.get(3).sendKeys(model.getLastName());

        Utils.createEmptyField(txtEmpFields.get(4));
        txtEmpFields.get(4).sendKeys(model.getEmployeeID());
        txtEmpFields.get(5).sendKeys(model.getUserName());
        txtEmpFields.get(6).sendKeys(model.getPassword());
        txtEmpFields.get(7).sendKeys(model.getPassword());

        btnSubmit.click();
    }

    public void searchEmp(String employeeID){
        searchEmp.get(1).sendKeys(employeeID);
        btnSubmit.click();

    }

}
