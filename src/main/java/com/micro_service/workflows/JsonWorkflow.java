package com.micro_service.workflows;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class JsonWorkflow {

    /**
     * Returns an array child property as a Java Stream.
     *
     * @param e    The element that contains the child array.
     * @param path The dot separated path to the child.
     * @return The array as a Stream
     */
    public static Stream<JsonElement> getJsonStream(JsonElement e, String path) {
        return StreamSupport.stream(getJsonArray(e, path).spliterator(), false);
    }

    /**
     * Returns a child array property.
     *
     * @param e    The element that contains the child array.
     * @param path The dot separated path to the child.
     * @return The array as JsonArray.
     */
    public static JsonArray getJsonArray(JsonElement e, String path) {
        JsonElement target = getTargetElement(e, path);

        return target.getAsJsonArray();
    }

    public static JsonObject jsonObject(JsonElement e, String path) {
        JsonElement target = getTargetElement(e, path);

        return target.getAsJsonObject();
    }



    /**
     * Returns a child string property.
     *
     * @param e    The element that contains the string.
     * @param path The dot separated path to the string.
     * @return The string child property
     */
    public static String jsonString(JsonElement e, String path) {
        JsonElement target = getTargetElement(e, path);

        if (target.isJsonNull()) {
            return null;
        }

        return target.getAsString();
    }

    /**
     * Returns a child number property.
     *
     * @param e    The element that contains the number.
     * @param path The dot separated path to the number.
     * @return The number child property
     */
    public static double jsonDouble(JsonElement e, String path) {
        JsonElement target = getTargetElement(e, path);

        return target.getAsDouble();
    }

    /**
     * Returns a child number property.
     *
     * @param e    The element that contains the number.
     * @param path The dot separated path to the number.
     * @return The number child property
     */
    public static int jsonInt(JsonElement e, String path) {
        JsonElement target = getTargetElement(e, path);

        return target.getAsInt();
    }
    
    private static JsonElement getTargetElement(JsonElement e, String path) {
        String parts[] = path.split("\\.");
        JsonElement target = e;
        
        for (String part : parts) {
            target = target.getAsJsonObject().get(part);
        }
        return target;
    }
    
    
}
