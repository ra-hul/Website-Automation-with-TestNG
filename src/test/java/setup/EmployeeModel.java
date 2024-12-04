package setup;

public class EmployeeModel {
    String firstName;
    String lastName;
    String userName;
    String employeeID;
    String password;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName(){
        return firstName;

    }

    public EmployeeModel(String firstName,String lastName, String employeeID, String userName,String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeID = employeeID;
        this.userName = userName;
        this.password = password;
    }

    public EmployeeModel(){}
}
