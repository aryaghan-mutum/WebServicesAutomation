package com.microservice.pojo.services;

import com.microservice.pojo.responses.MovieResponse;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@NoArgsConstructor
public class MovieService {

    public MovieResponse getMovieService() {
        WebClient webClient = getWebClient("").build();
        
        return webClient.get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(MovieResponse.class)
                .block();
    }

    public WebClient.Builder getWebClient(String url) {
        return WebClient.builder().baseUrl(url);
    }
    
}
