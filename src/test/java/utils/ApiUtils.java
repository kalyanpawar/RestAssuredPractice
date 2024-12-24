package utils;

import body.request.Email;
import body.request.Employee;
import com.google.gson.JsonObject;
import common.HttpRestContext;
import common.JsonUtils;
import common.Rest;
import common.URL;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;
import java.util.*;
import java.util.stream.Stream;
//import io.restassured.module.jsv.JsonSchemaValidator;

public class ApiUtils {

    public static HttpRestContext setContextForEmployees(){
        return new HttpRestContext();
    }

    // GET method
    public static JsonObject getEmployee(HttpRestContext context, int id) {
        context.setBaseURL(URL.BASE_URL);
        context.setURI(String.format(URL.GET_EMPLOYEE, id));
        context.setRequestContentType(ContentType.JSON);
        HttpRestContext response = Rest.GET(context);
        Assert.assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_OK);
        return JsonUtils.getJsonObjectForString(response.getResponseBody());
    }

    // POST method
    public static JsonObject addEmployee(HttpRestContext context, String firstName, String lastName, String dob, String dateOfJoining, Integer age,
                                         Double salary, LinkedHashMap<String, String> emails) {

        List<Email> emailList = new ArrayList<>();
        for(Map.Entry map: emails.entrySet()){
            Email email = new Email();
            email.setType((String) map.getKey());
            email.setEmail((String) map.getValue());
            emailList.add(email);
        }

        Employee employeeObj = new Employee();
        employeeObj.setFirstName(firstName);
        employeeObj.setLastName(lastName);
        employeeObj.setDob(dob);
        employeeObj.setAge(age);
        employeeObj.setSalary(salary);
        employeeObj.setEmails(emailList);
        employeeObj.setDateOfJoining(dateOfJoining);

        context.setBaseURL(URL.BASE_URL);
        context.setURI(URL.EMPLOYEES);
        context.setRequestContentType(ContentType.JSON);
        context.setRequestBody(JsonUtils.getObjectToString(employeeObj));
        context.setRequestContentType(ContentType.JSON);
        HttpRestContext response = Rest.POST(context);
        Assert.assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_CREATED);
        return JsonUtils.getJsonObjectForString(response.getResponseBody());
    }

    // PUT method
    public static JsonObject updateEmployee(HttpRestContext context, Integer empId, String firstName, String lastName, String dob, String dateOfJoining, Integer age,
                                 Double salary, LinkedHashMap<String, String> emails) {
        List<Email> emailList = new ArrayList<>();
        for(Map.Entry map: emails.entrySet()){
            Email email = new Email();
            email.setType((String) map.getKey());
            email.setEmail((String) map.getValue());
            emailList.add(email);
        }

        Employee employeeObj = new Employee();
        employeeObj.setFirstName(firstName);
        employeeObj.setLastName(lastName);
        employeeObj.setDob(dob);
        employeeObj.setAge(age);
        employeeObj.setSalary(salary);
        employeeObj.setEmails(emailList);
        employeeObj.setDateOfJoining(dateOfJoining);

        context.setBaseURL(URL.BASE_URL);
        context.setURI(String.format(URL.GET_EMPLOYEE, empId));
        context.setRequestContentType(ContentType.JSON);
        context.setRequestBody(JsonUtils.getObjectToString(employeeObj));
        context.setRequestContentType(ContentType.JSON);
        HttpRestContext response = Rest.PUT(context);
        Assert.assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_OK);
        return JsonUtils.getJsonObjectForString(response.getResponseBody());
    }

    // DELETE method
    public static void deleteEmployee(HttpRestContext context, Integer id) {
        context.setBaseURL(URL.BASE_URL);
        context.setURI(String.format(URL.GET_EMPLOYEE, id));
        context.setRequestContentType(ContentType.JSON);
        HttpRestContext response = Rest.DELETE(context);
        Assert.assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_NO_CONTENT);
    }

    // GET method
    public static JsonObject getEmployeeByFirstName(HttpRestContext context, String firstName) {
        context.setBaseURL(URL.BASE_URL);
        context.setURI(String.format(URL.GET_EMPLOYEE_BY_FIRST_NAME, firstName));
        context.setRequestContentType(ContentType.JSON);
        HttpRestContext response = Rest.GET(context);
        Assert.assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_OK);
        return JsonUtils.getJsonObjectForString(response.getResponseBody());
    }

    // GET method
    public static JsonObject getEmployeeByLastName(HttpRestContext context, String lastName) {
        context.setBaseURL(URL.BASE_URL);
        context.setURI(String.format(URL.GET_EMPLOYEE_BY_LAST_NAME, lastName));
        context.setRequestContentType(ContentType.JSON);
        HttpRestContext response = Rest.GET(context);
        Assert.assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_OK);
        return JsonUtils.getJsonObjectForString(response.getResponseBody());
    }

    // GET method
    public static JsonObject getEmployeeByFirstAndLastName(HttpRestContext context, String firstName, String lastName) {
        context.setBaseURL(URL.BASE_URL);
        context.setURI(String.format(URL.GET_EMPLOYEE_BY_FIRST_AND_LAST_NAME, firstName, lastName));
        context.setRequestContentType(ContentType.JSON);
        HttpRestContext response = Rest.GET(context);
        Assert.assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_OK);
        return JsonUtils.getJsonObjectForString(response.getResponseBody());
    }

    // GET method for Excel
    public static Response getEmployeeExcel(HttpRestContext context) {
        context.setBaseURL(URL.BASE_URL);
        context.setURI(URL.EMPLOYEES_EXPORT_EXCEL);
        context.setRequestContentType(ContentType.JSON);
        HttpRestContext response = Rest.GET(context);
        Assert.assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_OK);
        return response.getResponse();
    }

    // Generic method to validate JSON schema
//    public void validateJsonSchema(Response response, String schemaPath) {
//        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaPath));
//    }

    public JsonObject test(){
        RequestSpecification reqSpec = RestAssured.given();
        reqSpec.baseUri("");
        reqSpec.basePath("");
        reqSpec.contentType(ContentType.JSON);
        reqSpec.header("Authorization", "Bearer " + "token");

        Employee employeeObj = new Employee();
        employeeObj.setFirstName("firstName");
        employeeObj.setLastName("lastName");
        employeeObj.setDob("dob");
        employeeObj.setAge(34);
        employeeObj.setSalary((double)1237868);
        employeeObj.setDateOfJoining("dateOfJoining");

        reqSpec.body(employeeObj);
        Response response = reqSpec.post();


        Assert.assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_CREATED);
        return JsonUtils.getJsonObjectForString(response.getBody().asString());
    }

    @Test
    public void test1() {
        HashMap<Integer, String> map = new HashMap<>();

        List<String> list = Arrays.asList("Andrew", "Babu", "Amar");

        Stream<String> stream = list.stream();
        Stream<String> stream1 = stream.filter(i -> i.startsWith("A"));
        stream1.forEach(System.out::println);





    }
}

