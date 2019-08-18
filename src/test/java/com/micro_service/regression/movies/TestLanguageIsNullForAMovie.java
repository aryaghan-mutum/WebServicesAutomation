package com.micro_service.regression.movies;

import base.SuperClass;
import com.google.gson.JsonElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static com.micro_service.workflows.JsonPayloadWorkflow.retrieveMoviesServiceDoc;
import static com.micro_service.workflows.JsonWorkflow.getJsonStream;
import static com.micro_service.workflows.JsonWorkflow.getJsonString;
import static com.micro_service.workflows.JsonWorkflow.isUndefined;

public class TestLanguageIsNullForAMovie extends SuperClass {
    
    /**
     * Procedure 1: Test if the language is null
     * If the language == null, then the test FAILS
     * If the language != null then the test PASSES
     */
    @Test
    public void findLanguageIsNullProcedure1() throws FileNotFoundException {
        
        boolean isLanguageNullFound = getJsonStream(retrieveMoviesServiceDoc(), "payload.movies")
                .filter(this::isLanguageNullForProcedure1)
                .findAny()
                .isPresent();
        
        if (isLanguageNullFound) {
            Assertions.fail();
        }
    }
    
    private boolean isLanguageNullForProcedure1(JsonElement movie) {
        try {
            log("language is null for title: %s", getJsonString(movie, "title"));
            
            return getJsonString(movie, "movieLanguage.language") == null;
        } catch (Exception ex) {
            log("jsonString method throws exception when the tag is not present, make sure that is also treated as null");
            
            return true;
        }
    }
    
    /**
     * Procedure 2: Test if the language is null
     * If the language == null, then the test FAILS
     * If the language != null then the test PASSES
     */
    @Test
    public void findLanguageIsNullProcedure2() throws FileNotFoundException {
        
        long languageCount = getJsonStream(retrieveMoviesServiceDoc(), "payload.movies")
                .filter(this::isLanguageNullForProcedure2)
                .peek(movie -> log("language is null for title: %s", getJsonString(movie, "title")))
                .count();
        
        if (languageCount > 0) {
            Assertions.fail();
        }
    }
    
    private boolean isLanguageNullForProcedure2(JsonElement movie) {
        return isUndefined(movie, "movieLanguage.language") || getJsonString(movie, "movieLanguage.language") == null;
    }
    
    
}
