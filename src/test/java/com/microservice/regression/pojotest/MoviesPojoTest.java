package com.microservice.regression.pojotest;

import base.BaseTest;
import com.microservice.pojo.responses.Payload;
import com.microservice.pojo.services.MovieService;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class MoviesPojoTest extends BaseTest {
    
    @Test
    public void testMoviesPojo() {
        Arrays.asList(new MovieService().getMovieService())
                .forEach(movieResponse -> {
                    Payload p = movieResponse.getPayload();
                    System.out.println();
                });
    }
    
}
