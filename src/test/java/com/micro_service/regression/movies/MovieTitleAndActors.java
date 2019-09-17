package com.micro_service.regression.movies;

import base.SuperClass;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.micro_service.workflows.ConstantsWorkflow.ACTOR1;
import static com.micro_service.workflows.ConstantsWorkflow.ACTOR2;
import static com.micro_service.workflows.ConstantsWorkflow.CAST;
import static com.micro_service.workflows.ConstantsWorkflow.MOVIES;
import static com.micro_service.workflows.ConstantsWorkflow.TITLE;
import static com.micro_service.workflows.JsonPayloadWorkflow.retrieveMoviesServiceDoc;
import static com.micro_service.workflows.JsonWorkflow.getJsonStream;
import static com.micro_service.workflows.JsonWorkflow.getJsonString;

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
    
        Assert.assertEquals(movieMap.keySet().size(), 5);
        
        movieMap.keySet().stream().anyMatch(movieName -> movieName.equalsIgnoreCase("casino"));
        movieMap.keySet().stream().anyMatch(movieName -> movieName.equalsIgnoreCase("amadeus"));
        
        
        movieMap.entrySet().stream().collect(Collectors.toList());
    }
    
    
}
