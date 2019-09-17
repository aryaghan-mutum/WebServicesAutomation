package com.micro_service.regression.movies;

import base.SuperClass;
import com.google.gson.JsonElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static com.micro_service.workflows.ConstantsWorkflow.LANGUAGE;
import static com.micro_service.workflows.ConstantsWorkflow.MOVIES;
import static com.micro_service.workflows.ConstantsWorkflow.TITLE;
import static com.micro_service.workflows.JsonPayloadWorkflow.retrieveMoviesServiceDoc;
import static com.micro_service.workflows.JsonWorkflow.getJsonStream;
import static com.micro_service.workflows.JsonWorkflow.getJsonString;
import static com.micro_service.workflows.JsonWorkflow.isUndefined;

/**
 * @author Anurag Muthyam
 */

/**
 * Test if the language is null
 * If the language == null, then the test FAILS
 * If the language != null then the test PASSES
 */

public class TestLanguageIsNullForAMovie extends SuperClass {
    
    /**
     * Approach 1 using filter(), peek(), findAny() and isPresent():
     * The Test case returns a boolean value. if the value is true then FAILS
     */
    @Test
    public void findLanguageIsNullProcedure1() throws FileNotFoundException {
        
        boolean isLanguageNullFound = getJsonStream(retrieveMoviesServiceDoc(), MOVIES)
                .filter(this::isLanguageNullForProcedure1)
                .peek(movie -> log("language is null for title: %s", getJsonString(movie, TITLE)))
                .findAny()
                .isPresent();
        
        if (isLanguageNullFound) {
            Assertions.fail();
        }
    }
    
    private boolean isLanguageNullForProcedure1(JsonElement movie) {
        try {
            return getJsonString(movie, LANGUAGE) == null;
        } catch (Exception ex) {
            log("jsonString method throws exception when the tag is not present, make sure that is also treated as null");
            return true;
        }
    }
    
    /**
     * Approach 2 using filter(), peek(), and count():
     * The Test case returns a total count. if the count > 0 then FAILS
     */
    @Test
    public void findLanguageIsNullProcedure2() throws FileNotFoundException {
        
        long languageCount = getJsonStream(retrieveMoviesServiceDoc(), MOVIES)
                .filter(this::isLanguageNullForProcedure2)
                .peek(movie -> log("language is null for title: %s", getJsonString(movie, TITLE)))
                .count();
        
        if (languageCount > 0) {
            Assertions.fail();
        }
    }
    
    private boolean isLanguageNullForProcedure2(JsonElement movie) {
        return isUndefined(movie, LANGUAGE) || getJsonString(movie, LANGUAGE) == null;
    }
    
}
