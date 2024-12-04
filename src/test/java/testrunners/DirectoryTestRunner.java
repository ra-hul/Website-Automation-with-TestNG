package testrunners;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.DirectoryPage;
import pages.LoginPage;
import setup.Setup;
import utils.Utils;

import java.io.IOException;

public class DirectoryTestRunner extends Setup {

    DirectoryPage directoryPage;

    @BeforeTest
    public void doLogin(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("Admin", "admin123");
    }

    @Test(priority = 1, description = "Search a random non registered user")
    public void searchRandomUser() throws InterruptedException {
        directoryPage = new DirectoryPage(driver);
        directoryPage.leftMenuBar.get(8).click();
        directoryPage.searchEmpName("RandomUser0605");
        Thread.sleep(5000);

        String actualMessage = driver.findElement(By.cssSelector("[role=listbox]")).getText();
        String expectedMessage = "No Records Found";

        Assert.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test(priority = 2, description = "Search a valid employee name")
    public void searchForEmployee() throws IOException, ParseException, InterruptedException {
        directoryPage = new DirectoryPage(driver);
        directoryPage.leftMenuBar.get(8).click();

        JSONArray empArray = Utils.readJSON();
        JSONObject empObj = (JSONObject) empArray.get(empArray.size() -1 );
        String employeeName = (String) empObj.get("firstname");

        directoryPage.searchEmpName(employeeName);
        Thread.sleep(5000);

        String actualMessage = driver.findElement(By.cssSelector("[role=listbox]")).getText();

        Assert.assertTrue(actualMessage.contains(employeeName));

    }

    @AfterTest
    public void doLogout(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogout();
    }


}
