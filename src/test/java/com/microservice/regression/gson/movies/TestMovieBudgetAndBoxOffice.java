package com.microservice.regression.gson.movies;

import com.google.gson.JsonElement;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

import static com.microservice.workflows.ConstantsWorkflow.BOX_OFFICE;
import static com.microservice.workflows.ConstantsWorkflow.BUDGET;
import static com.microservice.workflows.ConstantsWorkflow.MOVIES;
import static com.microservice.workflows.ConstantsWorkflow.TITLE;
import static com.microservice.workflows.JsonPayloadWorkflow.retrieveMoviesServiceDoc;
import static com.microservice.workflows.JsonWorkflow.getJsonStream;
import static com.microservice.workflows.JsonWorkflow.getJsonString;
import static org.jsoup.helper.StringUtil.isBlank;
import static org.junit.Assert.fail;

/**
 * @author Anurag Muthyam
 * url: https://github.com/aryaghan-mutum
 */

@Slf4j
public class TestMovieBudgetAndBoxOffice {

    /**
     * Test budget and boxOffice of the movies
     * -> If budget, and boxOffice are null or empty, it is fine.
     * -> If budget or boxOffice is null or empty, the test case FAILS
     */
    @Test
    @DisplayName("Test Budget And BoxOffice Are Null Or Empty For Movies")
    public void testBudgetAndBoxOfficeAreNullOrEmptyForMovies() throws FileNotFoundException {
        Stream<JsonElement> movies = getJsonStream(retrieveMoviesServiceDoc(), MOVIES);
        AtomicBoolean isCostFieldNull = new AtomicBoolean(false);

        movies.forEach(movie -> {
            String movieTitle = getJsonString(movie, TITLE);
            String budget = getJsonString(movie, BUDGET);
            String boxOffice = getJsonString(movie, BOX_OFFICE);

            if (isBlank(budget) || isBlank(boxOffice)) {

                // if budget and boxOffice are all null, then skip.
                if (isBlank(budget) && isBlank(boxOffice)) {
                    isCostFieldNull.set(false);
                }
                // if budget or boxOffice is null or empty, the test case fails.
                else {
                    if (isBlank(budget)) {
                        isCostFieldNull.set(true);
                        log.error("budget: is null or empty for movieTitle: {} ", movieTitle);
                    }
                    if (isBlank(boxOffice)) {
                        isCostFieldNull.set(true);
                        log.error("boxOffice: is null or empty for movieTitle: {}", movieTitle);
                    }
                }
            }
        });

        if (isCostFieldNull.get()) {
            fail();
        }
    }

}
