package com.microservice.regression.movies;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static com.microservice.workflows.ConstantsWorkflow.DATE_RELEASED;
import static com.microservice.workflows.ConstantsWorkflow.MOVIES;
import static com.microservice.workflows.ConstantsWorkflow.TITLE;
import static com.microservice.workflows.JsonPayloadWorkflow.retrieveMoviesServiceDoc;
import static com.microservice.workflows.JsonWorkflow.getJsonStream;
import static com.microservice.workflows.JsonWorkflow.getJsonString;
import static com.microservice.workflows.Util.convertStringToLocalDateFormat;
import static com.microservice.workflows.Util.getCurrentYear;
import static com.microservice.workflows.Util.log;

/**
 * @author Anurag Muthyam
 * url: https://github.com/aryaghan-mutum
 */

public class TestMoviesReleasedLessThan40Years {
    
    /**
     * Approach 1 using forEach() :
     * 1. Gets movieTitle for each movie
     * 2. Gets yearReleased for each movie
     * 3. Calculates the difference between the (currentYear - yearReleased)
     * 4. If yearReleased < 40 then adds the movieTitle to the list
     */
    @Test
    @DisplayName("Test Total Count Of Movies Released Less Than 40 Years From Current Year")
    public void testTotalCountOfMoviesReleasedLessThan40YearsFromCurrentYear() throws FileNotFoundException {
        
        int countForProcedure1 = getTotalCountOfMoviesReleasedLessThan40YearsFromCurrentYearProcedure1();
        int countForProcedure2 = getTotalCountOfMoviesReleasedLessThan40YearsFromCurrentYearProcedure2();
        
        Assert.assertEquals(countForProcedure1, countForProcedure2);
        
    }
    
    @Step("Approach 1 using forEach(): Get Total Count Of Movies Released Less Than 40 Years From Current Year Procedure 1")
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
    
    @Step("Approach 2 using map(), filter() and count(): Get Total Count Of Movies Released Less Than 40 Years From Current Year Procedure 1")
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
