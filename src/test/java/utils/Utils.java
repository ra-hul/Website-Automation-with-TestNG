package utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import setup.EmployeeModel;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Utils {

    public static JSONArray readJSON() throws IOException, ParseException {

        String fileLocation ="./src/test/resources/users.json";

        JSONParser jsonParser = new JSONParser();

        JSONArray employeeArray = (JSONArray) jsonParser.parse(new FileReader(fileLocation));

        return employeeArray;
    }

    public static void writeJSON(JSONArray employeeArray) throws IOException {
        String fileLocation = "./src/test/resources/users.json";

        FileWriter writer = new FileWriter(fileLocation);
        writer.write(employeeArray.toJSONString());
        writer.flush();
    }

    public static void saveUsers(EmployeeModel model) throws IOException, ParseException {

        JSONArray employeeArray = readJSON();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstname", model.getFirstName());
        jsonObject.put("lastName", model.getLastName());
        jsonObject.put("userName",model.getUserName());
        jsonObject.put("employeeID", model.getEmployeeID());
        jsonObject.put("password", model.getPassword());

        employeeArray.add(jsonObject);

        writeJSON(employeeArray);
    }

    public static void createEmptyField(WebElement element){
        element.sendKeys(Keys.CONTROL + "A" + Keys.BACK_SPACE);
    }
    public static void scroll(WebDriver driver, int height) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, "+height+")");
    }

}
