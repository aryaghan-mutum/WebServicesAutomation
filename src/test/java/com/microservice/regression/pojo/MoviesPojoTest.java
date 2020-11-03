package com.microservice.regression.pojo;

import com.microservice.pojo.services.MovieService;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author Anurag Muthyam
 * url: https://github.com/aryaghan-mutum
 */

public class MoviesPojoTest {
    
    @Test
    public void testMoviesPojo() {
        Arrays.asList(new MovieService().getMovieService())
                .forEach(movieResponse -> {
                  System.out.println(movieResponse.toString());
                });
    }
    
}
