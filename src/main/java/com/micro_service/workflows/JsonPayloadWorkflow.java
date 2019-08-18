package com.micro_service.workflows;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;

import static com.micro_service.workflows.ConstantsWorkflow.MENU_SERVICE_PATH;

public class JsonPayloadWorkflow {
    
    public static JsonElement retrieveMenuServiceDoc() throws FileNotFoundException {
        
        FileReader reader = new FileReader(MENU_SERVICE_PATH);
        JsonParser jsonParser = new JsonParser();
        return jsonParser.parse(reader);
    }
    
    
}
