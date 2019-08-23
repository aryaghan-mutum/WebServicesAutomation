package com.micro_service.regression.movies;

import base.SuperClass;
import com.google.gson.JsonElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

import static com.micro_service.workflows.ConstantsWorkflow.ACTOR1;
import static com.micro_service.workflows.ConstantsWorkflow.ACTOR3;
import static com.micro_service.workflows.ConstantsWorkflow.CAST;
import static com.micro_service.workflows.ConstantsWorkflow.MOVIES;
import static com.micro_service.workflows.ConstantsWorkflow.TITLE;
import static com.micro_service.workflows.JsonPayloadWorkflow.retrieveMoviesServiceDoc;
import static com.micro_service.workflows.JsonWorkflow.getJsonStream;
import static com.micro_service.workflows.JsonWorkflow.getJsonString;
import static com.micro_service.workflows.JsonWorkflow.isUndefined;

public class TestLeadActorIsNullForAMovie extends SuperClass {
    
    /**
     * The Test case checks if each actor1 (lead actor) under cast is null in movies_service.json
     * If they are null, the test case logs a list of actor1 associated to the movie title
     * and fails the test
     */
    @Test
    public void findActor1ThatHasNull() throws FileNotFoundException {
        
        Stream<JsonElement> movies = getJsonStream(retrieveMoviesServiceDoc(), MOVIES);
        
        AtomicBoolean isActorNullFound = new AtomicBoolean(false);
        
        movies.forEach(movie -> {
            
            String movieTitle = getJsonString(movie, TITLE);
            
            long actor1Count = getJsonStream(movie, CAST)
                    .filter(cast -> isActorNull(cast, ACTOR1))
                    .peek(venue -> log("actor1 is null for movieTitle: %s", movieTitle))
                    .count();
            
            if (actor1Count > 0) {
                isActorNullFound.set(true);
            }
        });
        
        if (isActorNullFound.get()) {
            Assertions.fail();
        }
    }
    
    /**
     * The Test case checks if each actor2 under cast is not null in movies_service.json
     * If they are not null, the test case logs a list of actor3 associated to the movie title
     * and fails the test
     */
    @Test
    public void findActor3ThatHasNotNull() throws FileNotFoundException {
        
        Stream<JsonElement> movies = getJsonStream(retrieveMoviesServiceDoc(), MOVIES);
        
        AtomicBoolean isActor3NotNullFound = new AtomicBoolean(false);
        
        movies.forEach(movie -> {
            
            String movieTitle = getJsonString(movie, TITLE);
            
            String actor3 = getJsonStream(movie, CAST)
                    .map(cast -> getJsonString(cast, ACTOR3))
                    .reduce((a, b) -> a + "," + b)
                    .get();
            
            long actor3Count = getJsonStream(movie, CAST)
                    .filter(cast -> !isActorNull(cast, ACTOR3))
                    .peek(venue -> log("actor3 is %s for movieTitle: %s", actor3, movieTitle))
                    .count();
            
            if (actor3Count > 0) {
                isActor3NotNullFound.set(true);
            }
        });
        
        if (isActor3NotNullFound.get()) {
            Assertions.fail();
        }
    }
    
    private boolean isActorNull(JsonElement offering, String actor) {
        return isUndefined(offering, actor) || getJsonString(offering, actor) == null;
    }
}
