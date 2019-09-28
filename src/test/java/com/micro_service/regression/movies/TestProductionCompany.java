package com.micro_service.regression.movies;

import base.SuperClass;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicInteger;

import static com.micro_service.workflows.ConstantsWorkflow.DAYS;
import static com.micro_service.workflows.ConstantsWorkflow.MOVIES;
import static com.micro_service.workflows.ConstantsWorkflow.TITLE;
import static com.micro_service.workflows.JsonPayloadWorkflow.retrieveMoviesServiceDoc;
import static com.micro_service.workflows.JsonWorkflow.getJsonStream;
import static com.micro_service.workflows.JsonWorkflow.getJsonString;
import static com.micro_service.workflows.JsonWorkflow.isUndefined;
import static org.junit.jupiter.api.Assertions.fail;

public class TestProductionCompany extends SuperClass {

    /**
     * The Test case checks if each 'day' is null empty or missing to its associated 'menuTitle'
     */
    @Test
    public void testDayIsNullOrEmptyOrMissingForMovies() throws FileNotFoundException {

        AtomicInteger invalidDayCount = new AtomicInteger();

        getJsonStream(retrieveMoviesServiceDoc(), MOVIES)
                .forEach(movie -> {

                    String movieTitle = getJsonString(movie, TITLE);

                    boolean isDayFieldMissing = isUndefined(movie, DAYS);

                    try {
                        if (!isDayFieldMissing && getJsonStream(movie, DAYS).count() == 0) {
                            invalidDayCount.incrementAndGet();
                            log("ERROR: day is empty for movieTitle: %s", movieTitle);
                        }

                        if (isDayFieldMissing) {
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
