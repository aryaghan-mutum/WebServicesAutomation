package com.microservice.service.factory_pattern;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class JsonPost {
    
    /**
     * @return HTTP POST the service
     */
    public static JsonElement jsonPOST(String url, JsonElement body, String appKey) {
        Gson gson = new Gson();
        String requestJson = gson.toJson(body);
        
        return httpPOST(url, appKey, requestJson);
    }
    
    public static JsonElement httpPOST(String url, String appKey, String requestJson) {
        
        WebClient webClient = WebClient.create();
        
        String bodyString = webClient.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("AppKey", appKey)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(Mono.just(requestJson), String.class))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        
        if (bodyString != null) {
            JsonParser parser = new JsonParser();
            
            return parser.parse(bodyString);
        } else {
            return null;
        }
    }
    
}
