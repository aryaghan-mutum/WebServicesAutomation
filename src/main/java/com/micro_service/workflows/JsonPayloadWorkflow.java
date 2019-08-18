package com.micro_service.workflows;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;

import static com.micro_service.workflows.ConstantsWorkflow.MENU_SERVICE_PATH;
import static com.micro_service.workflows.ConstantsWorkflow.MOVIE_SERVICE_PATH;

public class JsonPayloadWorkflow {
    
    public static JsonElement retrieveMenuServiceDoc() throws FileNotFoundException {
        return retrieveServiceDocument(MENU_SERVICE_PATH);
    }
    
    public static JsonElement retrieveMoviesServiceDoc() throws FileNotFoundException {
        return retrieveServiceDocument(MOVIE_SERVICE_PATH);
    }
    
    private static JsonElement retrieveServiceDocument(String servicePath) throws FileNotFoundException {
        FileReader reader = new FileReader(servicePath);
        JsonParser jsonParser = new JsonParser();
        return jsonParser.parse(reader);
    }
    
    
}