package com.microservice.regression.movies;

import base.SuperClass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.microservice.workflows.ConstantsWorkflow.ACTOR1;
import static com.microservice.workflows.ConstantsWorkflow.ACTOR2;
import static com.microservice.workflows.ConstantsWorkflow.CAST;
import static com.microservice.workflows.ConstantsWorkflow.MOVIES;
import static com.microservice.workflows.ConstantsWorkflow.TITLE;
import static com.microservice.workflows.JsonPayloadWorkflow.retrieveMoviesServiceDoc;
import static com.microservice.workflows.JsonWorkflow.getJsonStream;
import static com.microservice.workflows.JsonWorkflow.getJsonString;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Anurag Muthyam
 */

public class MovieTitleAndActors extends SuperClass {
    
    /**
     * 1. Get actor1 and actor2 from movies.service.json
     * 2. Store them in a list
     * 3. Assert
     */
    @Test
    @DisplayName("Test Movie Title And Actors")
    public void testMovieTitleAndActors() throws FileNotFoundException {

        Map<String, List<String>> movieMap = new HashMap<>();
        
        getJsonStream(retrieveMoviesServiceDoc(), MOVIES)
                .forEach(movie -> {
                    
                    String movieTitle = getJsonString(movie, TITLE);
                    
                    String actor1 = getJsonStream(movie, CAST)
                            .filter(cast -> getJsonString(cast, ACTOR1) != null)
                            .map(cast -> getJsonString(cast, ACTOR1))
                            .reduce((a, b) -> a + "," + b)
                            .get();
    
                    String actor2 = getJsonStream(movie, CAST)
                            .filter(cast -> getJsonString(cast, ACTOR2) != null)
                            .map(cast -> getJsonString(cast, ACTOR2))
                            .reduce((a, b) -> a + "," + b)
                            .get();
                    
                    List<String> actors = Arrays.asList(actor1, actor2);
    
                    movieMap.put(movieTitle, actors);
                });
    
        assertEquals(movieMap.keySet().size(), 5);
        
        movieMap.keySet().stream().anyMatch(movieName -> movieName.equalsIgnoreCase("casino"));
        movieMap.keySet().stream().anyMatch(movieName -> movieName.equalsIgnoreCase("amadeus"));
        
        movieMap.entrySet().stream().collect(Collectors.toList());
    }
    
    
}
