package com.microservice.regression.movies;

import base.SuperClass;
import com.google.gson.JsonElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static com.microservice.workflows.ConstantsWorkflow.LANGUAGE;
import static com.microservice.workflows.ConstantsWorkflow.MOVIES;
import static com.microservice.workflows.ConstantsWorkflow.TITLE;
import static com.microservice.workflows.JsonPayloadWorkflow.retrieveMoviesServiceDoc;
import static com.microservice.workflows.JsonWorkflow.getJsonStream;
import static com.microservice.workflows.JsonWorkflow.getJsonString;
import static com.microservice.workflows.JsonWorkflow.isFieldUndefined;
import static org.junit.Assert.fail;

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
    @DisplayName("Find language is null Procedure 1")
    public void findLanguageIsNullProcedure1() throws FileNotFoundException {
        
        boolean isLanguageNullFound = getJsonStream(retrieveMoviesServiceDoc(), MOVIES)
                .filter(this::isLanguageNullForProcedure1)
                .peek(movie -> log("language is null for title: %s", getJsonString(movie, TITLE)))
                .findAny()
                .isPresent();
        
        if (isLanguageNullFound) {
            fail();
        }
    }
    
    @Step("check language is null for procedure 1")
    private boolean isLanguageNullForProcedure1(JsonElement movie) {
        try {
            return getJsonString(movie, LANGUAGE) == null;
        } catch (Exception ex) {
            log("Exception: %s" + ex.getMessage());
            log("jsonString method throws exception when the tag is not present, make sure that is also treated as null");
            return true;
        }
    }
    
    /**
     * Approach 2 using filter(), peek(), and count():
     * The Test case returns a total count. if the count > 0 then FAILS
     */
    @Test
    @DisplayName("Find language is null Procedure 2")
    public void findLanguageIsNullProcedure2() throws FileNotFoundException {
        
        long languageCount = getJsonStream(retrieveMoviesServiceDoc(), MOVIES)
                .filter(this::isLanguageNullForProcedure2)
                .peek(movie -> log("language is null for title: %s", getJsonString(movie, TITLE)))
                .count();
        
        if (languageCount > 0) {
            fail();
        }
    }
    
    @Step("check language is null for procedure 2")
    private boolean isLanguageNullForProcedure2(JsonElement movie) {
        return isFieldUndefined(movie, LANGUAGE) || getJsonString(movie, LANGUAGE) == null;
    }
    
}
