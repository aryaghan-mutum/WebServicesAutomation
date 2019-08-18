package com.micro_service.regression.movies;

import base.SuperClass;
import com.google.gson.JsonElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

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
    public void testLeadActorIsNull() throws FileNotFoundException {
        
        Stream<JsonElement> movies = getJsonStream(retrieveMoviesServiceDoc(), "payload.movies");
        
        AtomicBoolean isActorNullFound = new AtomicBoolean(false);
        
        movies.forEach(movie -> {
            
            String movieTitle = getJsonString(movie, "title");
            
            long leadActorCount = getJsonStream(movie, "cast")
                    .filter(this::isActor1Null)
                    .peek(venue -> log("actor1 is null for movieTitle: %s", movieTitle))
                    .count();
            
            if (leadActorCount > 0) {
                isActorNullFound.set(true);
            }
        });
        
        if (isActorNullFound.get()) {
            Assertions.fail();
        }
    }
    
    private boolean isActor1Null(JsonElement offering) {
        return isUndefined(offering, "actor1") || getJsonString(offering, "actor1") == null;
    }
}
