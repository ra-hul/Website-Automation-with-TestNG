package testrunners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import setup.Setup;

public class LoginAdminTestRunner extends Setup {
    LoginPage loginPage;

    @Test(priority = 1, description = "Admin login with invalid username & password")
    public void doLoginWithInvalidCred(){
        loginPage = new LoginPage(driver);
        loginPage.doLogin("rahul","rahul123");

        WebElement alertText = driver.findElement(By.className("oxd-text--p"));


        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(alertText.getText(),"Invalid credentials");
        softAssert.assertAll();
    }


    @Test(priority = 2, description = "Admin logins with capital username & password")
    public void doLoginWithInvalidCredTwo(){
        loginPage = new LoginPage(driver);
        loginPage.doLogin("ADMIN","ADMIN123");

        WebElement alertText = driver.findElement(By.className("oxd-text--p"));

        String actualText = alertText.getText();
        String expectedText = "Invalid credentials";

        Assert.assertEquals(actualText,expectedText);

        loginPage.doLogout();
    }

    @Test(priority = 3, description = "Admin logins with capital username & correct password")
    public void doLoginWithInvalidCredThree(){
        loginPage = new LoginPage(driver);
        loginPage.doLogin("ADMIN", "admin123");

        WebElement alertText = driver.findElement(By.className("oxd-text--p"));



        loginPage.doLogout();
    }


    @Test(priority = 4, description = "Admin logins with valid cred")
    public void doLoginWithValidCred(){
        loginPage = new LoginPage(driver);
        loginPage.doLogin("Admin", "admin123");
        Assert.assertTrue(loginPage.btnProfileImage.isDisplayed());
    }

    @Test(priority = 5, description = "Admin can logout")
    public void doLogoutCheck(){
        loginPage = new LoginPage(driver);
        loginPage.doLogout();

        WebElement forgotPassElem = driver.findElement(By.className("orangehrm-login-forgot-header"));
        Assert.assertTrue(forgotPassElem.isDisplayed());
    }


}
