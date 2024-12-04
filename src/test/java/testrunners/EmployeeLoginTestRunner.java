package testrunners;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import setup.Setup;
import utils.Utils;

import java.io.IOException;

public class EmployeeLoginTestRunner extends Setup {
    LoginPage loginPage;

    @Test(priority = 1, description = "Login with wrong cred of employee")
    public  void doLoginWithWrongCred() throws IOException, ParseException {
        loginPage = new LoginPage(driver);

        JSONArray empArray = Utils.readJSON();
        JSONObject empObj = (JSONObject) empArray.get(empArray.size() - 1);

        String username = (String) empObj.get("userName");

        loginPage.doLogin(username, "wrong@123");

        WebElement alertText = driver.findElement(By.className("oxd-text--p"));

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(alertText.getText(),"Invalid credentials");
        softAssert.assertAll();
    }

    @Test(priority = 2, description = "Login with valid cred of employee")
    public void doLoginWithValidCred() throws IOException, ParseException {
        loginPage = new LoginPage(driver);

        String expectedHeader = driver.findElement(By.className("orangehrm-login-title")).getText();
        String actualHeader = "Login";

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(actualHeader.contains(expectedHeader));

        JSONArray empArr = Utils.readJSON();
        JSONObject empObj = (JSONObject) empArr.get(empArr.size() - 1);
        String username = (String) empObj.get("userName");
        String password = (String) empObj.get("password");
        String firstName = (String) empObj.get("firstname");
        String lastName = (String) empObj.get("lastName");

        loginPage.doLogin(username, password);

        String expectedName = firstName + " " + lastName;

        String actualName = driver.findElement(By.className("oxd-userdropdown-name")).getText();

        softAssert.assertEquals(actualName, expectedName);
        softAssert.assertAll();
    }

    @Test(priority = 3, description = "Admin can log out")
    public  void doLogout(){
        loginPage = new LoginPage(driver);
        loginPage.doLogout();

        String expectedHeader = driver.findElement(By.className("orangehrm-login-title")).getText();
        String actualHeader = "Login";

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(actualHeader.contains(expectedHeader));
        softAssert.assertAll();
    }

    @Test(priority = 4, description = "User back to the landing page")
    public void backToLogin(){
        driver.navigate().back();

        String actualURL = driver.getCurrentUrl();
        String expectedURL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualURL, expectedURL);
        softAssert.assertAll();


    }
}
