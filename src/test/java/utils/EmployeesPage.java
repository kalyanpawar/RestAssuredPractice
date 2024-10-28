package utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.List;

public class EmployeesPage {

    public static void validateIdNotNull(JsonObject response){
        Assert.assertNotNull(response.get("id").getAsInt());
    }

    public static void validateEmployeeResponse(JsonObject postResponse, String firstName, String lastName, String dob, String dateOfJoining, int age,
                                            Double salary, HashMap<String, String> emails){
        SoftAssert sa = new SoftAssert();
        sa.assertEquals(postResponse.get("firstName").getAsString(), firstName);
        sa.assertEquals(postResponse.get("lastName").getAsString(), lastName);
        sa.assertEquals(postResponse.get("dateOfJoining").getAsString(), dateOfJoining);
        sa.assertEquals(postResponse.get("age").getAsInt(), age);
        sa.assertEquals(postResponse.get("salary").getAsDouble(), salary);
        sa.assertAll();
    }

    public static void validateEmailDetails(JsonObject response, List<String> emailDetails){
        JsonArray emailList = response.get("emails").getAsJsonArray();
        for(int i = 0; i<emailList.size()/2; i+=2){
            Assert.assertEquals(emailList.get(i).getAsJsonObject().get("type").getAsString(), emailDetails.get(i));
            Assert.assertEquals(emailList.get(i).getAsJsonObject().get("email").getAsString(), emailDetails.get(i+1));
        }
    }

    public static Integer getEmloyeeId(JsonObject response){
        return response.get("id").getAsInt();
    }
}
