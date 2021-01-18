package com.microservice.regression.gson.movies;

import lombok.extern.slf4j.Slf4j;
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
 * url: https://github.com/aryaghan-mutum
 */

@Slf4j
public class TestDays {
    
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
                            log.error("ERROR: day is empty for movieTitle: {}", movieTitle);
                        } else if (isDayFieldMissing) {
                            log.warn("WARN: day field is missing for movieTitle: {}", movieTitle);
                        }
                    } catch (Exception e) {
                        invalidDayCount.incrementAndGet();
                        log.info("ERROR: day is null for movieTitle: {}", movieTitle);
                        log.info("Exception: {}", e);
                    }
                });
        
        if (invalidDayCount.get() > 0) {
            fail();
        }
    }
}
