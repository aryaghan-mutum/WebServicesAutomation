package com.micro_service.regression.movies;

import base.SuperClass;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.micro_service.workflows.ConstantsWorkflow.DATE_RELEASED;
import static com.micro_service.workflows.ConstantsWorkflow.MOVIES;
import static com.micro_service.workflows.ConstantsWorkflow.TITLE;
import static com.micro_service.workflows.ConstantsWorkflow.YEAR_RELEASED;
import static com.micro_service.workflows.JsonPayloadWorkflow.retrieveMoviesServiceDoc;
import static com.micro_service.workflows.JsonWorkflow.getJsonInt;
import static com.micro_service.workflows.JsonWorkflow.getJsonStream;
import static com.micro_service.workflows.JsonWorkflow.getJsonString;
import static com.micro_service.workflows.Util.convertStringToLocalDateFormat;

public class TestMoviesReleasedAfter1980 extends SuperClass {
    
    /**
     * Operations used: foreach():
     * 1. Gets movieTitle for each movie
     * 2. Gets yearReleased for each movie
     * 3. If yearReleased > 1980 then add the movieTitle to a list
     * 4. If movieTitle doesn't contain "Amadeus" then FAIL
     */
    @Test
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
            Assert.fail();
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
    public void getListOfMoviesReleasedYearsAfter1980() throws FileNotFoundException {
        
        List<Integer> moviesReleasedAfter1980List = getJsonStream(retrieveMoviesServiceDoc(), MOVIES)
                .map(movie -> getJsonInt(movie, YEAR_RELEASED))
                .filter(yearReleased -> yearReleased > 1980)
                .collect(Collectors.toList());
        
        Assert.assertEquals(moviesReleasedAfter1980List.size(), 3);
        
        if (!moviesReleasedAfter1980List.contains(1984)) {
            Assertions.fail();
        }
    }
    
}
