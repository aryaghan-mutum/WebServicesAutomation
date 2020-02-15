package com.microservice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public abstract class BaseResponse {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseResponse.class);
    
    /**
     * Get the webClient object using builder pattern
     */
    public static WebClient getWebClient(String url) {
        return WebClient.builder().baseUrl(url).build();
    }
    
    /**
     * @param responseString response payload in string format
     * @return Converts the responseString into JsonElement and return the response
     */
    public static JsonElement getJsonResponse(String responseString) {
        
        JsonParser parser = new JsonParser();
        JsonElement response = parser.parse(responseString);
        
        return response;
    }
    
    /**
     * The procedure saves the response in the local when the service is fired.\
     *
     * @param filePath save response as a path
     * @param document the service response
     */
    public static void saveJSON(String filePath, JsonElement document) throws IOException {
        try (Writer writer = new FileWriter(filePath)) {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .serializeNulls()
                    .create();
            
            gson.toJson(document, writer);
        }
    }
    
}
