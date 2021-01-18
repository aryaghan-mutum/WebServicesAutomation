package com.microservice.regression.gson.movies;

import com.google.gson.JsonElement;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

import static com.microservice.workflows.ConstantsWorkflow.ACTOR1;
import static com.microservice.workflows.ConstantsWorkflow.ACTOR3;
import static com.microservice.workflows.ConstantsWorkflow.CAST;
import static com.microservice.workflows.ConstantsWorkflow.MOVIES;
import static com.microservice.workflows.ConstantsWorkflow.TITLE;
import static com.microservice.workflows.JsonPayloadWorkflow.retrieveMoviesServiceDoc;
import static com.microservice.workflows.JsonWorkflow.getJsonStream;
import static com.microservice.workflows.JsonWorkflow.getJsonString;
import static com.microservice.workflows.JsonWorkflow.isFieldUndefined;
import static org.junit.Assert.fail;

/**
 * @author Anurag Muthyam
 * url: https://github.com/aryaghan-mutum
 */

@Slf4j
public class TestLeadActorIsNullForAMovie {
    
    /**
     * The Test case checks if each actor1 (lead actor) under cast is null in movies_service.json
     * If they are null, the test case logs a list of actor1 associated to the movie title
     * and fails the test
     */
    @Test
    @DisplayName("Find Actor1 That Has Null")
    public void findActor1ThatHasNull() throws FileNotFoundException {
        Stream<JsonElement> movies = getJsonStream(retrieveMoviesServiceDoc(), MOVIES);
        
        AtomicBoolean isActorNullFound = new AtomicBoolean(false);
        
        movies.forEach(movie -> {
            String movieTitle = getJsonString(movie, TITLE);
            long actor1Count = getJsonStream(movie, CAST)
                    .filter(cast -> isActorNull(cast, ACTOR1))
                    .peek(venue -> log.info("actor1 is null for movieTitle: {}", movieTitle))
                    .count();
            
            if (actor1Count > 0) {
                isActorNullFound.set(true);
            }
        });
        
        if (isActorNullFound.get()) {
            fail();
        }
    }
    
    /**
     * The Test case checks if each actor2 under cast is not null in movies_service.json
     * If they are not null, the test case logs a list of actor3 associated to the movie title
     * and fails the test
     */
    @Test
    @DisplayName("Find Actor3 That Has Null")
    public void findActor3ThatHasNotNull() throws FileNotFoundException {
        Stream<JsonElement> movies = getJsonStream(retrieveMoviesServiceDoc(), MOVIES);

        AtomicBoolean isActor3NotNullFound = new AtomicBoolean(false);
        
        movies.forEach(movie -> {
            
            String movieTitle = getJsonString(movie, TITLE);
            
            try {
                String actor3 = getJsonStream(movie, CAST)
                        .map(cast -> getJsonString(cast, ACTOR3))
                        .reduce((a, b) -> a + "," + b)
                        .get();
    
                long actor3Count = getJsonStream(movie, CAST)
                        .filter(cast -> !isActorNull(cast, ACTOR3))
                        .peek(venue -> log.info("actor3 is {} for movieTitle: {}", actor3, movieTitle))
                        .count();
    
    
                if (actor3Count > 0) {
                    isActor3NotNullFound.set(true);
                }
            }catch (Exception e) {
                log.info("actor3 is null for movieTitle: {}", movieTitle);
            }
            
        });
        
        if (isActor3NotNullFound.get()) {
            fail();
        }
    }
    
    @Step("check if actor is null")
    private boolean isActorNull(JsonElement offering, String actor) {
        return isFieldUndefined(offering, actor) || getJsonString(offering, actor) == null;
    }
}
