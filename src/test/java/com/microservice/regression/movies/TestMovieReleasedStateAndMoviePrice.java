package com.microservice.regression.movies;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.microservice.workflows.ConstantsWorkflow.COUNTRY_RELEASED;
import static com.microservice.workflows.ConstantsWorkflow.MOVIES;
import static com.microservice.workflows.ConstantsWorkflow.MOVIE_ITEM;
import static com.microservice.workflows.ConstantsWorkflow.MOVIE_RELEASE;
import static com.microservice.workflows.ConstantsWorkflow.MOVIE_RELEASED_PRICE;
import static com.microservice.workflows.ConstantsWorkflow.MOVIE_RELEASED_STATE;
import static com.microservice.workflows.ConstantsWorkflow.TITLE;
import static com.microservice.workflows.JsonPayloadWorkflow.retrieveMoviesServiceDoc;
import static com.microservice.workflows.JsonWorkflow.getJsonStream;
import static com.microservice.workflows.JsonWorkflow.getJsonString;
import static com.microservice.workflows.JsonWorkflow.isFieldUndefined;
import static com.microservice.workflows.Util.log;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.platform.commons.util.StringUtils.isBlank;

/**
 * @author Anurag Muthyam
 * url: https://github.com/aryaghan-mutum
 */

public class TestMovieReleasedStateAndMoviePrice {
    
    /**
     * The Test case validates 'movieReleasedState' & 'moveReleasedPrice' are both null/empty associated to each
     * 'countryReleased' and for each 'movieTitle'
     * If both 'movieReleasedState' & 'moveReleasedPrice' are null/empty, then the test fails
     */
    @Test
    @DisplayName("Test MovieReleasedState And MoveReleasedPrice Null Or Missing")
    public void testMovieReleasedStateAndMoveReleasedPriceNullOrMissing() throws FileNotFoundException {
        
        AtomicBoolean isMovieReleasedStateAndMoveReleasedPriceNullOrMissing = new AtomicBoolean(false);
        
        getJsonStream(retrieveMoviesServiceDoc(), MOVIES)
                .forEach(movie -> {
                    
                    String movieTitle = getJsonString(movie, TITLE);
                    
                    getJsonStream(movie, MOVIE_RELEASE)
                            .forEach(movieRelease -> {
                                
                                String countryReleased = getJsonString(movieRelease, COUNTRY_RELEASED);
                                
                                getJsonStream(movieRelease, MOVIE_ITEM)
                                        .forEach(movieItem -> {
                                            
                                            boolean isMovieReleasedStateFieldMissing = isFieldUndefined(movieItem, MOVIE_RELEASED_STATE);
                                            boolean isMoveReleasedPriceFieldMissing = isFieldUndefined(movieItem, MOVIE_RELEASED_PRICE);
                                            
                                            if (!isMovieReleasedStateFieldMissing &&
                                                    !isMoveReleasedPriceFieldMissing &&
                                                    isBlank(getJsonString(movieItem, MOVIE_RELEASED_STATE)) &&
                                                    isBlank(getJsonString(movieItem, MOVIE_RELEASED_PRICE))) {
                                                
                                                isMovieReleasedStateAndMoveReleasedPriceNullOrMissing.set(true);
                                                log("ERROR: movieReleasedState & moveReleasedPrice are both null/empty "
                                                                + "under countryReleased: %s for "
                                                                + "movieTitle: %s",
                                                        countryReleased,
                                                        movieTitle);
                                            } else if (isMovieReleasedStateFieldMissing && isMoveReleasedPriceFieldMissing) {
                                                log("WARN: movieReleasedState & moveReleasedPrice field are both "
                                                                + "missing under countryReleased: %s for "
                                                                + "movieTitle: %s",
                                                        countryReleased,
                                                        movieTitle);
                                            }
                                            
                                        });
                            });
                    
                });
        
        if (isMovieReleasedStateAndMoveReleasedPriceNullOrMissing.get()) {
            fail();
        }
    }
}
