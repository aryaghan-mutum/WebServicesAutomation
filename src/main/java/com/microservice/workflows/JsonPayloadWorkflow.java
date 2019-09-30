package com.microservice.workflows;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;

import static com.microservice.workflows.ConstantsWorkflow.COUNTRY_BY_CONTINENT;
import static com.microservice.workflows.ConstantsWorkflow.COUNTRY_BY_POPULATION_DENSITY;
import static com.microservice.workflows.ConstantsWorkflow.MOVIE_SERVICE_PATH;

public class JsonPayloadWorkflow {
    
    public static JsonElement retrieveMoviesServiceDoc() throws FileNotFoundException {
        return retrieveServiceDocument(MOVIE_SERVICE_PATH);
    }
    
    public static JsonElement retrieveCountryByPopulationDensityServiceDoc() throws FileNotFoundException {
        return retrieveServiceDocument(COUNTRY_BY_POPULATION_DENSITY);
    }
    
    public static JsonElement retrieveCountryByContinentServiceDoc() throws FileNotFoundException {
        return retrieveServiceDocument(COUNTRY_BY_CONTINENT);
    }
    
    private static JsonElement retrieveServiceDocument(String servicePath) throws FileNotFoundException {
        FileReader reader = new FileReader(servicePath);
        JsonParser jsonParser = new JsonParser();
        return jsonParser.parse(reader);
    }
    
    
}
