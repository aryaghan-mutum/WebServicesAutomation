package com.microservice.pojo.services;

import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@NoArgsConstructor
public abstract class BaseService {

    /**
     * @param url
     * @return
     */
    public WebClient.Builder getWebClient(String url) {
        return WebClient.builder().baseUrl(url);
    }
    
}
