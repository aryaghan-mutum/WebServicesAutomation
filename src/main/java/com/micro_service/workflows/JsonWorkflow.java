package com.micro_service.workflows;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class JsonWorkflow {
    
    public static Stream<JsonElement> getJsonStream(JsonElement e, String path) {
        return StreamSupport.stream(getJsonArray(e, path).spliterator(), false);
    }
    
    public static JsonArray getJsonArray(JsonElement e, String path) {
        JsonElement target = getTargetElement(e, path);
        
        return target.getAsJsonArray();
    }
    
    public static JsonObject getJsonObject(JsonElement e, String path) {
        JsonElement target = getTargetElement(e, path);
        
        return target.getAsJsonObject();
    }
    
    public static String getJsonString(JsonElement e, String path) {
        JsonElement target = getTargetElement(e, path);
        
        if (target.isJsonNull()) {
            return null;
        }
        
        return target.getAsString();
    }
    
    public static double getJsonDouble(JsonElement e, String path) {
        JsonElement target = getTargetElement(e, path);
        
        return target.getAsDouble();
    }
    
    public static int getJsonInt(JsonElement e, String path) {
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
    
    public static boolean isDefined(JsonElement e, String path) {
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
    
    public static boolean isUndefined(JsonElement e, String path) {
        return !isDefined(e, path);
    }
    
    
}
