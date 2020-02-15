package com.microservice.builder_pattern;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import static com.microservice.BaseResponse.getWebClient;

public class jsonDelete {
    
    public static void httpDELETE(String url, String appKey) {
        
        WebClient webClient = getWebClient(url);
        
        webClient.delete()
                .header("AppKey", appKey)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
    
}
