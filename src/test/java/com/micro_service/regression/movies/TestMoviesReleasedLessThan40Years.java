package com.micro_service.regression.movies;

import base.SuperClass;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static com.micro_service.workflows.ConstantsWorkflow.DATE_RELEASED;
import static com.micro_service.workflows.ConstantsWorkflow.MOVIES;
import static com.micro_service.workflows.ConstantsWorkflow.TITLE;
import static com.micro_service.workflows.JsonPayloadWorkflow.retrieveMoviesServiceDoc;
import static com.micro_service.workflows.JsonWorkflow.getJsonStream;
import static com.micro_service.workflows.JsonWorkflow.getJsonString;
import static com.micro_service.workflows.Util.convertStringToLocalDateFormat;
import static com.micro_service.workflows.Util.getCurrentYear;

/**
 * @author Anurag Muthyam
 */

public class TestMoviesReleasedLessThan40Years extends SuperClass {
    
    /**
     * Approach 1 using forEach() :
     * 1. Gets movieTitle for each movie
     * 2. Gets yearReleased for each movie
     * 3. Calculates the difference between the (currentYear - yearReleased)
     * 4. If yearReleased < 40 then adds the movieTitle to the list
     */
    @Test
    public void testTotalCountOfMoviesReleasedLessThan40YearsFromCurrentYear() throws FileNotFoundException {
        
        int countForProcedure1 = getTotalCountOfMoviesReleasedLessThan40YearsFromCurrentYearProcedure1();
        int countForProcedure2 = getTotalCountOfMoviesReleasedLessThan40YearsFromCurrentYearProcedure2();
        
        Assert.assertEquals(countForProcedure1, countForProcedure2);
        
    }
    
    /**
     * Approach 1 using forEach():
     */
    public int getTotalCountOfMoviesReleasedLessThan40YearsFromCurrentYearProcedure1() throws FileNotFoundException {
        
        List<String> moviesReleasedLessThan40YearsFromTodayList = new ArrayList<>();
        
        getJsonStream(retrieveMoviesServiceDoc(), MOVIES)
                .forEach(movie -> {
                    
                    String movieTitle = getJsonString(movie, TITLE);
                    
                    int yearReleased = convertStringToLocalDateFormat(getJsonString(movie, DATE_RELEASED)).getYear();
                    
                    int yearDifference = getCurrentYear() - yearReleased;
                    
                    if (yearDifference < 40) {
                        log("movieTitle: %s was released %s year ago", movieTitle, yearDifference);
                        moviesReleasedLessThan40YearsFromTodayList.add(movieTitle);
                    }
                    
                });
        
        return moviesReleasedLessThan40YearsFromTodayList.size();
    }
    
    /**
     * Approach 2 using map(), filter() and count():
     */
    public int getTotalCountOfMoviesReleasedLessThan40YearsFromCurrentYearProcedure2() throws FileNotFoundException {
    
        long moviesReleasedLessThan40YearsFromTodayCount = getJsonStream(retrieveMoviesServiceDoc(), MOVIES)
                .map(movie -> getJsonString(movie, DATE_RELEASED))
                .map(movie -> convertStringToLocalDateFormat(movie).getYear())
                .map(movie -> getCurrentYear() - movie)
                .filter(yearDifference -> yearDifference < 40)
                .count();
        
        log("There are %s movies released 40 years ago", moviesReleasedLessThan40YearsFromTodayCount);
        
        return (int) moviesReleasedLessThan40YearsFromTodayCount;
    }
    
}
