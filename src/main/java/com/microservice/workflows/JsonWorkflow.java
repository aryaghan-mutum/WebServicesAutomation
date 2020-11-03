package com.microservice.workflows;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author Anurag Muthyam
 * url: https://github.com/aryaghan-mutum
 */

public class JsonWorkflow {

    /**
     *
     * @param e
     * @param path
     * @return
     */
    public static Stream<JsonElement> getJsonStream(JsonElement e, String path) {
        return StreamSupport.stream(getJsonArray(e, path).spliterator(), false);
    }

    /**
     *
     * @param e
     * @param path
     * @return
     */
    public static JsonArray getJsonArray(JsonElement e, String path) {
        JsonElement target = getTargetElement(e, path);
        return target.getAsJsonArray();
    }

    /**
     *
     * @param e
     * @param path
     * @return
     */
    public static JsonObject getJsonObject(JsonElement e, String path) {
        JsonElement target = getTargetElement(e, path);
        return target.getAsJsonObject();
    }

    /**
     *
     * @param e
     * @param path
     * @return
     */
    public static String getJsonString(JsonElement e, String path) {
        JsonElement target = getTargetElement(e, path);
        if (target.isJsonNull()) {
            return null;
        }
        return target.getAsString();
    }

    /**
     *
     * @param e
     * @param path
     * @return
     */
    public static double getJsonDouble(JsonElement e, String path) {
        JsonElement target = getTargetElement(e, path);
        return target.getAsDouble();
    }

    /**
     *
     * @param e
     * @param path
     * @return
     */
    public static int getJsonInt(JsonElement e, String path) {
        JsonElement target = getTargetElement(e, path);
        return target.getAsInt();
    }

    /**
     *
     * @param e
     * @param path
     * @return
     */
    private static JsonElement getTargetElement(JsonElement e, String path) {
        String parts[] = path.split("\\.");
        JsonElement target = e;
        
        for (String part : parts) {
            target = target.getAsJsonObject().get(part);
        }
        return target;
    }

    /**
     *
     * @param e
     * @param path
     * @return
     */
    public static boolean isFieldDefined(JsonElement e, String path) {
        String parts[] = path.split("\\.");
        JsonElement target = e;
        
        for (String part : parts) {
            JsonObject obj = target.getAsJsonObject();
            
            if (obj.has(part)) {
                target = obj.get(part);
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param e
     * @param path
     * @return
     */
    public static boolean isFieldUndefined(JsonElement e, String path) {
        return !isFieldDefined(e, path);
    }
}
