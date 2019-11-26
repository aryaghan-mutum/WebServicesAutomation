package com.microservice.service.builder_pattern;

import com.google.gson.JsonElement;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import static com.microservice.service.BaseResponse.getJsonResponse;
import static com.microservice.service.BaseResponse.getWebClient;

public class JsonGet {
    
    /**
     * @param url ECR endpoint
     * @return the ECR response based on the environment
     */
    public static JsonElement jsonGet(String url, String appKey) {
        
        WebClient webClient = getWebClient(url);
        
        String bodyString = webClient.get()
                .header("AppKey", appKey)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        
        return getJsonResponse(bodyString);
    }
    
    /**
     * @param url        Ship Deployment endpoint
     * @return Ship Deployment response based on the environment
     */
    public static JsonElement jsonGet(String url, String username, String password) {
        
        WebClient webClient = getWebClient(url);
        
        String responseString = webClient.get()
                .headers(headers -> headers.setBasicAuth(username, password))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        
        return getJsonResponse(responseString);
    }
}
