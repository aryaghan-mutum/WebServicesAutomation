package com.microservice.service.factory_pattern;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class jsonDelete {
    
    public static void httpDELETE(String url, String appKey) {
        
        WebClient webClient = WebClient.create();
        
        webClient.delete()
                .uri(url)
                .header("AppKey", appKey)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
    
}
