package com.micro_service.workflows;


import java.io.InputStream;

public class SettingsManager {
    
    private static final String JSON_PATH = "/settings.json";
    
    public static Settings loadConfig() {
        InputStream inputStream = SettingsManager.class.getResourceAsStream(JSON_PATH);
    //    JsonParser parser = new JsonParser();
    //    JsonElement root = parser.parse(new InputStreamReader(inputStream));
    //    JsonObject rootObj = root.getAsJsonObject();
        Settings contour = new Settings();

     //   contour.setJson(rootObj);

     //   contour.setShipCode(rootObj.getAsJsonPrimitive("shipCode").getAsString());
     //   contour.setSailDate(rootObj.getAsJsonPrimitive("sailDate").getAsString());
     //   contour.setEnvironment(rootObj.getAsJsonPrimitive("environment").getAsString());

      //  setupEnvironment(config);

        return contour;
    }
}
