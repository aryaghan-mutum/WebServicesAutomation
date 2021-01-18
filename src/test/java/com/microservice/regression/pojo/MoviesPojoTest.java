package com.microservice.regression.pojo;

import com.microservice.pojo.services.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author Anurag Muthyam
 * url: https://github.com/aryaghan-mutum
 */

@Slf4j
public class MoviesPojoTest {
    
    @Test
    public void testMoviesPojo() {
        Arrays.asList(new MovieService().getMovieService())
                .forEach(movieResponse -> log.info(movieResponse.toString()));
    }
    
}
