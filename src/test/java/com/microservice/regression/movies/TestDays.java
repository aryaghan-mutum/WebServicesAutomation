package com.microservice.regression.movies;

import base.SuperClass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicInteger;

import static com.microservice.workflows.ConstantsWorkflow.DAYS;
import static com.microservice.workflows.ConstantsWorkflow.MOVIES;
import static com.microservice.workflows.ConstantsWorkflow.TITLE;
import static com.microservice.workflows.JsonPayloadWorkflow.retrieveMoviesServiceDoc;
import static com.microservice.workflows.JsonWorkflow.getJsonStream;
import static com.microservice.workflows.JsonWorkflow.getJsonString;
import static com.microservice.workflows.JsonWorkflow.isFieldUndefined;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * @author Anurag Muthyam
 */

public class TestDays extends SuperClass {
    
    /**
     * The Test case checks if each 'day' is null empty or missing to its associated 'menuTitle'
     */
    @Test
    @DisplayName("Test Day Is Null Or Empty Or Missing For Movies")
    public void testDayIsNullOrEmptyOrMissingForMovies() throws FileNotFoundException {
        
        AtomicInteger invalidDayCount = new AtomicInteger();
        
        getJsonStream(retrieveMoviesServiceDoc(), MOVIES)
                .forEach(movie -> {
                    
                    String movieTitle = getJsonString(movie, TITLE);
                    boolean isDayFieldMissing = isFieldUndefined(movie, DAYS);
                    
                    try {
                        if (!isDayFieldMissing && getJsonStream(movie, DAYS).count() == 0) {
                            invalidDayCount.incrementAndGet();
                            log("ERROR: day is empty for movieTitle: %s", movieTitle);
                        } else if (isDayFieldMissing) {
                            log("WARN: day field is missing for movieTitle: %s", movieTitle);
                        }
                    } catch (Exception e) {
                        invalidDayCount.incrementAndGet();
                        log("ERROR: day is null for movieTitle: %s", movieTitle);
                    }
                });
        
        if (invalidDayCount.get() > 0) {
            fail();
        }
    }
}
