package com.micro_service.regression;

import base.SuperClass;
import com.google.gson.JsonElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

import static com.micro_service.workflows.JsonPayloadWorkflow.retrieveMoviesServiceDoc;
import static com.micro_service.workflows.JsonWorkflow.getJsonStream;
import static com.micro_service.workflows.JsonWorkflow.getJsonString;

public class TestMovieBudgetAndBoxOffice extends SuperClass {
    
    /**
     * Test budget and boxOffice of the movies
     * -> If budget, and boxOffice are null or empty, it is fine.
     * -> If budget or boxOffice is null or empty, the test case FAILS
     */
    @Test
    public void testBudgetAndBoxOfficeAreNullOrEmptyForMovies() throws FileNotFoundException {
        
        Stream<JsonElement> movies = getJsonStream(retrieveMoviesServiceDoc(), "payload.movies");
        
        AtomicBoolean isCostFieldNull = new AtomicBoolean(false);
        
        movies.forEach(movie -> {
            
            String movieTitle = getJsonString(movie, "title");
            String budget = getJsonString(movie, "cost.budget");
            String boxOffice = getJsonString(movie, "cost.boxOffice");
            
            if (StringUtils.isBlank(budget) || StringUtils.isBlank(boxOffice)) {
                
                // if budget and boxOffice are all null, then skip.
                if (StringUtils.isBlank(budget) && StringUtils.isBlank(boxOffice)) {
                    isCostFieldNull.set(false);
                }
                // if budget or boxOffice is null or empty, the test case fails.
                else {
                    if (StringUtils.isBlank(budget)) {
                        isCostFieldNull.set(true);
                        log("budget: is null or empty for movieTitle: %s ", movieTitle);
                    }
                    if (StringUtils.isBlank(boxOffice)) {
                        isCostFieldNull.set(true);
                        log("boxOffice: is null or empty for movieTitle: %s", movieTitle);
                    }
                }
            }
        });
        
        if (isCostFieldNull.get()) {
            log("FAILED");
            Assertions.fail();
        }
    }
    
}
