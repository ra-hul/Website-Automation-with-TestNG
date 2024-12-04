package testrunners;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import pages.MyInfoPage;
import setup.Setup;
import utils.Utils;

import java.io.IOException;
import java.util.Random;

public class MyInfoTestRunner extends Setup {
    MyInfoPage myInfoPage;

    @BeforeTest
    public void doLogin() throws IOException, ParseException {
        JSONArray empArray = Utils.readJSON();

        JSONObject empObj = (JSONObject) empArray.get(empArray.size() - 1);

        String username = (String) empObj.get("userName");
        String password = (String) empObj.get("password");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(username,password);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(loginPage.btnProfileImage.isDisplayed());
        softAssert.assertAll();
    }

    public void myInfoPage(){
        myInfoPage = new MyInfoPage(driver);
        myInfoPage.leftMenuBar.get(2).click();

        String actualTile = driver.findElement(By.className("orangehrm-main-title")).getText();

        String expectedTitle = "Personal Details";

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualTile,expectedTitle);
        softAssert.assertAll();
    }

    @Test(priority = 1, description = "Selects Multiple gender by employees")
    public void selectWithMultiGender() throws InterruptedException {
        myInfoPage();
        Utils.scroll(driver, 200);
        Thread.sleep(4000);

        myInfoPage.radioMaleFeMale.get(0).click();
        myInfoPage.radioMaleFeMale.get(1).click();
    }

    @Test(priority = 2, description = "Select one gender by employees")
    public  void selectOneGender() throws InterruptedException {
        myInfoPage();
        Utils.scroll(driver, 200);
        Thread.sleep(4000);

        Random random = new Random();
        int chooseGender = random.nextInt(2);
        myInfoPage.radioMaleFeMale.get(chooseGender).click();
        myInfoPage.btnSave.get(0).click();
        driver.navigate().refresh();

        Thread.sleep(5000);
        Utils.scroll(driver,200);
        Thread.sleep(5000);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(driver.findElement(By.xpath("//input[contains(@value, '" + (chooseGender + 1) + "')]"))
                .isSelected());
        softAssert.assertAll();

    }

    @Test(priority = 3, description = "Selecting multiple blood type by employees")
    public void empMultipleBloodType() throws InterruptedException {
        myInfoPage();
        Utils.scroll(driver,800);
        Thread.sleep(4000);

        myInfoPage.dropdownBloodType.get(2).sendKeys("o");
        myInfoPage.dropdownBloodType.get(2).sendKeys(Keys.ENTER);

        myInfoPage.dropdownBloodType.get(2).sendKeys("a");
        myInfoPage.dropdownBloodType.get(2).sendKeys(Keys.ENTER);

        Thread.sleep(4000);

        String actualBloodType = myInfoPage.dropdownBloodType.get(2).getText();
        String expectedBloodType="A+";

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(!actualBloodType.contains("O+"));
        softAssert.assertEquals(actualBloodType,expectedBloodType);
        softAssert.assertAll();
    }


    @Test(priority = 4, description = "Employees can select their blood type")
    public  void empSelectBloodType() throws InterruptedException {
        myInfoPage();
        Utils.scroll(driver,800);
        Thread.sleep(4000);

        myInfoPage.dropdownBloodType.get(2).sendKeys("o");
        myInfoPage.dropdownBloodType.get(2).sendKeys(Keys.ENTER);
        myInfoPage.btnSave.get(1).click();

        driver.navigate().refresh();
        Thread.sleep(4000);
        Utils.scroll(driver, 800);

        String actualBloodType = myInfoPage.dropdownBloodType.get(2).getText();
        String expectedBloodType="O+";

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualBloodType,expectedBloodType);
        softAssert.assertAll();
    }

    @AfterTest
    public void doLogout(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogout();
    }


}
