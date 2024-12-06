package test;

import com.google.gson.JsonObject;
import common.GenerateExcel;
import common.HttpRestContext;
import enums.EmailType;
import generator.DataGenerator;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.ApiUtils;
import utils.EmployeesPage;

import java.util.*;

public class ApiTest {

    @Test
    public static void bookingTest() {
        String emailTypeHome = EmailType.HOME.getValue();
        String emailTypeOffice = EmailType.OFFICE.getValue();
        generator.Employee employee = DataGenerator.createEmployeeData();
        String emailAddress1 = employee.getEmail();
        String emailAddress2 = employee.getEmail();
        String firstName = employee.getFirstName();
        String lastName = employee.getLastName();
        int age = employee.getAge();
        String dob = employee.getDOB();
        Double salary = 35000.00;
        String dateOfJoining = "1992-09-26";
        LinkedHashMap<String, String> emails = new LinkedHashMap<>();
        emails.put(emailTypeHome, emailAddress1);
        emails.put(emailTypeOffice, emailAddress2);
        List<String> emailList = Arrays.asList(emailTypeHome, emailAddress1, emailTypeOffice, emailAddress2);
        int newAge = employee.getAge();
        String newDob = employee.getDOB();
        HttpRestContext context = ApiUtils.setContextForEmployees();

        // Example Post request
        JsonObject addEmployeeResponse = ApiUtils.addEmployee(context, firstName, lastName, dob, dateOfJoining, age, salary, emails);
        EmployeesPage.validateIdNotNull(addEmployeeResponse);
        EmployeesPage.validateEmployeeResponse(addEmployeeResponse, firstName, lastName, dob, dateOfJoining, age, salary, emails);
        EmployeesPage.validateEmailDetails(addEmployeeResponse, emailList);
        int empId = EmployeesPage.getEmloyeeId(addEmployeeResponse);

        JsonObject getEmployeeResponse = ApiUtils.getEmployee(context, empId);
        EmployeesPage.validateEmployeeResponse(getEmployeeResponse, firstName, lastName, dob, dateOfJoining, age, salary, emails);
        EmployeesPage.validateEmailDetails(getEmployeeResponse, emailList);

        JsonObject getEmployeeByFirstNameResponse = ApiUtils.getEmployeeByFirstName(context, firstName);
        EmployeesPage.validateEmployeeResponse(getEmployeeByFirstNameResponse, firstName, lastName, dob, dateOfJoining, age, salary, emails);
        EmployeesPage.validateEmailDetails(getEmployeeByFirstNameResponse, emailList);

        JsonObject getEmployeeByLastNameResponse = ApiUtils.getEmployeeByLastName(context, lastName);
        EmployeesPage.validateEmployeeResponse(getEmployeeByLastNameResponse, firstName, lastName, dob, dateOfJoining, age, salary, emails);
        EmployeesPage.validateEmailDetails(getEmployeeByLastNameResponse, emailList);

        JsonObject getEmployeeByFirstAndLastNameResponse = ApiUtils.getEmployeeByFirstAndLastName(context, firstName, lastName);
        EmployeesPage.validateEmployeeResponse(getEmployeeByFirstAndLastNameResponse, firstName, lastName, dob, dateOfJoining, age, salary, emails);
        EmployeesPage.validateEmailDetails(getEmployeeByFirstAndLastNameResponse, emailList);

        JsonObject updateEmployeeResponse = ApiUtils.updateEmployee(context, empId, firstName, lastName, newDob, dateOfJoining, newAge, salary, emails);
        EmployeesPage.validateEmployeeResponse(updateEmployeeResponse, firstName, lastName, newDob, dateOfJoining, newAge, salary, emails);
        EmployeesPage.validateEmailDetails(updateEmployeeResponse, emailList);

        ApiUtils.deleteEmployee(context, empId);
    }

    @Test
    public static void excelDataTest(){
        HttpRestContext context = ApiUtils.setContextForEmployees();
        Response response = ApiUtils.getEmployeeExcel(context);
        String filePath = "downloads/output.xlsx";

        GenerateExcel.generateExcelFromResponse(response, filePath);

    }
}

