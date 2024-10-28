package common;

import com.google.gson.*;
import lombok.SneakyThrows;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonUtils {

    // Method to convert JSON string to JsonObject
    public static JsonObject getJsonObjectForString(String jsonString) {
        try {
            return JsonParser.parseString(jsonString).getAsJsonObject();
        } catch (JsonSyntaxException e) {
            // Handle invalid JSON input
            System.err.println("Failed to parse JSON: " + e.getMessage());
            return null;
        }
    }

    /**
     *
     * @param object
     * @return String
     */
    public static <T> String getObjectToString(T object) {
        return new GsonBuilder().setPrettyPrinting().serializeNulls().create().toJson(object);
    }

    /**
     * Get the JsonObject from the json file.
     * @param fileName name of the file from which data is read
     * @return JsonObject of tags from the given file
     */
    @SneakyThrows
    public static JsonObject getJsonObject(String fileName) {
        String jsonStr = new String(Files
                .readAllBytes(Paths.get(fileName)),
                StandardCharsets.UTF_8);
        return new Gson().fromJson(jsonStr, JsonObject.class);
    }

    /**
     * Get the class object from given string.
     * @param value value string of json value
     * @param valueType the class type which object should be returned
     * @return given valueType class object
     */
    public static <T> T getObjectFromString(String value, Class<T> valueType) {
        return new Gson().fromJson(value, valueType);
    }
}

