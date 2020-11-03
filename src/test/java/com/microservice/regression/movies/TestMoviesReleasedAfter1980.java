package com.microservice.regression.movies;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static com.microservice.workflows.ConstantsWorkflow.DATE_RELEASED;
import static com.microservice.workflows.ConstantsWorkflow.MOVIES;
import static com.microservice.workflows.ConstantsWorkflow.TITLE;
import static com.microservice.workflows.ConstantsWorkflow.YEAR_RELEASED;
import static com.microservice.workflows.JsonPayloadWorkflow.retrieveMoviesServiceDoc;
import static com.microservice.workflows.JsonWorkflow.getJsonInt;
import static com.microservice.workflows.JsonWorkflow.getJsonStream;
import static com.microservice.workflows.JsonWorkflow.getJsonString;
import static com.microservice.workflows.Util.convertStringToLocalDateFormat;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.fail;

/**
 * @author Anurag Muthyam
 * url: https://github.com/aryaghan-mutum
 */

public class TestMoviesReleasedAfter1980 {
    
    /**
     * Operations used: foreach():
     * 1. Gets movieTitle for each movie
     * 2. Gets yearReleased for each movie
     * 3. If yearReleased > 1980 then add the movieTitle to a list
     * 4. If movieTitle doesn't contain "Amadeus" then FAIL
     */
    @Test
    @DisplayName("Get List Of Movies Released After 1980")
    public void getListOfMoviesReleasedAfter1980() throws FileNotFoundException {
        
        List<String> moviesReleasedAfter1980List = new ArrayList<>();
        
        getJsonStream(retrieveMoviesServiceDoc(), MOVIES)
                .forEach(movie -> {
                    
                    String movieTitle = getJsonString(movie, TITLE);
                    
                    int yearReleased = convertStringToLocalDateFormat(getJsonString(movie, DATE_RELEASED)).getYear();
                    
                    if (yearReleased > 1980) {
                        moviesReleasedAfter1980List.add(movieTitle);
                    }
                    
                });
        
        if (!moviesReleasedAfter1980List.contains("Amadeus")) {
            fail();
        }
    }
    
    /**
     * Operations used: map(), filter() and collect():
     * 1. Gets yearReleased for each movie
     * 3. If yearReleased > 1980 then add the yearReleased to a list
     * 4. If moviesReleasedAfter1980List.size() != 3 then FAIL
     * 5. If yearReleased != 1984 then FAIL
     */
    @Test
    @DisplayName("Get List Of Movies Released Years After 1980")
    public void getListOfMoviesReleasedYearsAfter1980() throws FileNotFoundException {
        
        List<Integer> moviesReleasedAfter1980List = getJsonStream(retrieveMoviesServiceDoc(), MOVIES)
                .map(movie -> getJsonInt(movie, YEAR_RELEASED))
                .filter(yearReleased -> yearReleased > 1980)
                .collect(toList());
        
        Assert.assertEquals(moviesReleasedAfter1980List.size(), 3);
        
        if (!moviesReleasedAfter1980List.contains(1984)) {
            fail();
        }
    }
    
}
