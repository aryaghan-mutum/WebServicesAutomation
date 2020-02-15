package com.microservice.factory_pattern;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class JsonGet {
    
    public static JsonElement jsonGET(String url, String appKey) {
        
        WebClient webClient = WebClient.create();
        
        String bodyString = webClient.get()
                .uri(url)
                .header("AppKey", appKey)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        
        JsonParser parser = new JsonParser();
        
        JsonElement doc = parser.parse(bodyString);
        
        return doc;
    }
    
}
