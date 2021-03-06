package com.microservice.regression.gson.movies;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.microservice.workflows.ConstantsWorkflow.COUNTRY_RELEASED;
import static com.microservice.workflows.ConstantsWorkflow.MOVIES;
import static com.microservice.workflows.ConstantsWorkflow.MOVIE_ITEM;
import static com.microservice.workflows.ConstantsWorkflow.MOVIE_RELEASE;
import static com.microservice.workflows.ConstantsWorkflow.MOVIE_RELEASED_STATE;
import static com.microservice.workflows.ConstantsWorkflow.TITLE;
import static com.microservice.workflows.JsonPayloadWorkflow.retrieveMoviesServiceDoc;
import static com.microservice.workflows.JsonWorkflow.getJsonStream;
import static com.microservice.workflows.JsonWorkflow.getJsonString;
import static com.microservice.workflows.JsonWorkflow.isFieldUndefined;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.platform.commons.util.StringUtils.isBlank;

/**
 * @author Anurag Muthyam
 * url: https://github.com/aryaghan-mutum
 */

@Slf4j
public class TestMovieReleasedState {

    /**
     * Procedure 1 using isFieldUndefined():
     * The Test case validates 'movieReleasedState' is null/empty/missing for each 'movieTitle'
     */
    @Test
    @DisplayName("Test Movie ReleasedState is Null Or Missing Procedure 1")
    public void testMovieReleasedStateNullOrMissingProcedure1() throws FileNotFoundException {

        AtomicBoolean isMoveReleasedStateNullOrMissing = new AtomicBoolean(false);

        getJsonStream(retrieveMoviesServiceDoc(), MOVIES)
                .forEach(movie -> {

                    String movieTitle = getJsonString(movie, TITLE);

                    getJsonStream(movie, MOVIE_RELEASE)
                            .forEach(movieRelease -> {

                                String countryReleased = getJsonString(movieRelease, COUNTRY_RELEASED);

                                getJsonStream(movieRelease, MOVIE_ITEM)
                                        .forEach(movieItem -> {

                                            boolean isMenuItemTitleMissing = isFieldUndefined(movieItem, MOVIE_RELEASED_STATE);

                                            if (!isMenuItemTitleMissing &&
                                                    isBlank(getJsonString(movieItem, MOVIE_RELEASED_STATE))) {

                                                isMoveReleasedStateNullOrMissing.set(true);
                                                log.error("movieReleasedState is null under countryReleased: {} for "
                                                                + "movieTitle: {}",
                                                        countryReleased,
                                                        movieTitle);
                                            }

                                            if (isMenuItemTitleMissing) {
                                                log.warn("movieReleasedState field is missing under countryReleased: {} for "
                                                                + "movieTitle: {}",
                                                        countryReleased,
                                                        movieTitle);
                                            }
                                        });


                            });
                });

        if (isMoveReleasedStateNullOrMissing.get()) {
            fail();
        }
    }

    /**
     * Procedure 2 using try and catch block:
     * The Test case validates 'movieReleasedState' is null/missing for each 'movieTitle'
     */
    @Test
    @DisplayName("Test Movie ReleasedState is Null Or Missing Procedure 2")
    public void testMovieReleasedStateNullOrMissingProcedure2() throws FileNotFoundException {

        AtomicBoolean isMoveReleasedStateNullOrMissing = new AtomicBoolean(false);

        getJsonStream(retrieveMoviesServiceDoc(), MOVIES)
                .forEach(movie -> {

                    String movieTitle = getJsonString(movie, TITLE);

                    getJsonStream(movie, MOVIE_RELEASE)
                            .forEach(movieRelease -> {

                                String countryReleased = getJsonString(movieRelease, COUNTRY_RELEASED);

                                getJsonStream(movieRelease, MOVIE_ITEM)
                                        .forEach(movieItem -> {

                                            try {

                                                String movieReleasedState = getJsonString(movieItem, MOVIE_RELEASED_STATE);

                                                if (isBlank(movieReleasedState)) {
                                                    isMoveReleasedStateNullOrMissing.set(true);
                                                    log.error("movieReleasedState is null/empty under countryReleased: {} for "
                                                                    + "movieTitle: {}",
                                                            countryReleased,
                                                            movieTitle);
                                                }
                                            } catch (Exception e) {
                                                log.warn("movieReleasedState field is missing under countryReleased: {} for "
                                                                + "movieTitle: {}",
                                                        countryReleased,
                                                        movieTitle);
                                            }

                                        });


                            });
                });

        if (isMoveReleasedStateNullOrMissing.get()) {
            fail();
        }
    }

}
