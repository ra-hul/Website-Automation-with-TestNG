package testrunners;

import com.github.javafaker.Faker;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import pages.PIMPage;
import setup.EmployeeModel;
import setup.Setup;
import utils.Utils;

import java.io.IOException;

public class PIMTestRunner extends Setup {
    PIMPage pimPage;
    SoftAssert softAssert;

    @BeforeTest
    public void doLogin(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("Admin", "admin123");
    }

    @BeforeMethod
    public void setUp() {
        // Initialize SoftAssert before each test
        softAssert = new SoftAssert();
    }

    public void PIMPageEmp() throws InterruptedException {
        pimPage = new PIMPage(driver);
        pimPage.leftMenuBar.get(1).click();

        Thread.sleep(3000);

        String actualText = driver.findElements(By.className("oxd-text")).get(14).getText();

        System.out.println(actualText);

        String expectedText = "Records Found";

        Assert.assertTrue(actualText.contains(expectedText));

        pimPage.btnAdd.get(2).click();
        pimPage.loginDetailsToggle.click();
    }

    @Test(priority = 1, description = "Create employee without mandatory fields")
    public void createEmpWithoutMandatoryFields() throws InterruptedException {
        PIMPageEmp();

        pimPage.createEmp("Rogan", "Josh", "", "", "roganJosh12345","roganJosh12345");
        pimPage.btnSubmit.click();

        String actualAlert = driver.findElement(By.className("oxd-input-field-error-message")).getText();
        System.out.println(actualAlert);

        String expectedAlert = "Required";

        Assert.assertTrue(actualAlert.contains(expectedAlert));
    }


    @Test(priority = 2, description = "Try to create an employee with existing credentials")
    public void createEmpWithExistingID() throws InterruptedException {
        PIMPageEmp();

        pimPage.createEmp("Rogun","Paul","0375","Rog-Paly","12345678ra","12345678ra");
        pimPage.btnSubmit.click();

        String actualAlert = driver.findElement(By.className("oxd-input-field-error-message")).getText();
        System.out.println(actualAlert);

        String expectedAlert = "Employee Id already exists";

        softAssert.assertEquals(actualAlert, expectedAlert);
        softAssert.assertAll();
    }



    @Test(priority = 3, description = "Created a valid employee with valid credentials")
    public void createEmpWithValidCred() throws InterruptedException, IOException, ParseException {
        PIMPageEmp();

        Faker faker = new Faker();

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        int min = 100;
        int max = 500;

        int range = max - min + 1;

        String employeeID = Integer.toString((int) (Math.random() * range) + min);

        String userName = firstName.toLowerCase() + "-" + lastName.toLowerCase();
        String password = "@" + firstName + "#" + Integer.toString((int) (Math.random() * 100));


        EmployeeModel empModel = new EmployeeModel();
        empModel.setFirstName(firstName);
        empModel.setLastName(lastName);
        empModel.setEmployeeID(employeeID);
        empModel.setUserName(userName);
        empModel.setPassword(password);

        pimPage.createEmp(empModel);

        Thread.sleep(6000);

        String actualTitle = driver.findElements(By.className("oxd-text--h6")).get(2).getText();

        System.out.println(actualTitle);

        String expectedTitle = "Personal Details";

        Assert.assertTrue(actualTitle.contains(expectedTitle));

        Utils.saveUsers(empModel);
    }


    @Test(priority = 4, description = "Searching employee using employee ID")
    public void searchUsingEmpID() throws IOException, ParseException, InterruptedException {
        JSONArray jsonArray = Utils.readJSON();

        JSONObject jsonObject = (JSONObject) jsonArray.get(jsonArray.size() - 1);
        String searchEmpID = (String) jsonObject.get("employeeID");

        pimPage.leftMenuBar.get(1).click();
        pimPage.searchEmp(searchEmpID);
        Thread.sleep(5000);

        String actualEmpID = driver.findElements(By.cssSelector("[role=cell]")).get(1).getText();
        System.out.println(actualEmpID);

        softAssert.assertEquals(searchEmpID, actualEmpID);
        softAssert.assertAll();
    }

    @Test(priority = 5,description = "Searching an employee with invalid employee ID")
    public void searchUsingInvalidID() throws InterruptedException {
        pimPage.leftMenuBar.get(1).click();

        pimPage.searchEmp("546789021");

        Thread.sleep(4000);

        String actualAlert = driver.findElements(By.className("oxd-text--span")).get(12).getText();

        String expectedAlert = "No Records Found";

        softAssert.assertEquals(actualAlert, expectedAlert);
        softAssert.assertAll();
    }


    @AfterTest
    public void doLogout(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogout();
    }

}
