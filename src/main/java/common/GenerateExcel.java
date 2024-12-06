package common;

import io.restassured.response.Response;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class GenerateExcel {

    public static void generateExcelFromResponse(Response response, String filePath){
        // Validate response
        try (InputStream inputStream = response.asInputStream();
             FileOutputStream outputStream = new FileOutputStream(filePath)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            System.out.println("Excel file downloaded successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
