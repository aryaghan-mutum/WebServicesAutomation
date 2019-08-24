package com.micro_service.regression.movies;

import base.SuperClass;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.micro_service.workflows.ConstantsWorkflow.DATE_RELEASED;
import static com.micro_service.workflows.ConstantsWorkflow.MOVIES;
import static com.micro_service.workflows.ConstantsWorkflow.TITLE;
import static com.micro_service.workflows.JsonPayloadWorkflow.retrieveMoviesServiceDoc;
import static com.micro_service.workflows.JsonWorkflow.getJsonStream;
import static com.micro_service.workflows.JsonWorkflow.getJsonString;
import static com.micro_service.workflows.Util.convertStringToLocalDateFormat;

public class TestMoviesReleasedAfter1980 extends SuperClass {
    
    /**
     * Approach 1 using forEach() :
     */
    @Test
    public List<String> getListOfMoviesReleasedAfter1980Procedure1() throws FileNotFoundException {
        
        List<String> moviesReleasedAfter1980List= new ArrayList<>();
        
        getJsonStream(retrieveMoviesServiceDoc(), MOVIES)
                .forEach(movie -> {
                    
                    String movieTitle = getJsonString(movie, TITLE);
                    
                    int yearReleased = convertStringToLocalDateFormat(getJsonString(movie, DATE_RELEASED)).getYear();
                    
                    if (yearReleased > 1980) {
                        moviesReleasedAfter1980List.add(movieTitle);
                    }
                    
                });
        
        return moviesReleasedAfter1980List;
    }
    
    /**
     * Approach 1 using  :
     */
    public List<String> getListOfMoviesReleasedAfter1980Procedure2() throws FileNotFoundException {
        
        List<String> moviesReleasedAfter1980List= new ArrayList<>();
        
        getJsonStream(retrieveMoviesServiceDoc(), MOVIES)
                .forEach(movie -> {
                    
                    String movieTitle = getJsonString(movie, TITLE);
                    
                    int yearReleased = convertStringToLocalDateFormat(getJsonString(movie, DATE_RELEASED)).getYear();
                    
                    if (yearReleased > 1980) {
                        moviesReleasedAfter1980List.add(movieTitle);
                    }
                    
                });
        
        return moviesReleasedAfter1980List;
    }
    
}
