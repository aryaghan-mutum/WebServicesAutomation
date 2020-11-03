package com.microservice.regression.movies;

import com.google.gson.JsonElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static com.microservice.workflows.ConstantsWorkflow.MOVIES;
import static com.microservice.workflows.ConstantsWorkflow.TITLE;
import static com.microservice.workflows.JsonPayloadWorkflow.retrieveMoviesServiceDoc;
import static com.microservice.workflows.JsonWorkflow.getJsonStream;
import static com.microservice.workflows.JsonWorkflow.getJsonString;
import static com.microservice.workflows.JsonWorkflow.isFieldUndefined;
import static io.netty.util.internal.StringUtil.EMPTY_STRING;
import static org.jsoup.helper.StringUtil.isBlank;
import static org.junit.Assert.fail;
import static com.microservice.workflows.Util.log;

/**
 * @author Anurag Muthyam
 */

public class SectionName {

    /**
     * Procedure 1: Test if the sectionName is null
     * If the sectionName == null, then the test FAILS
     * If the sectionName != null then the test PASSES
     * <p>
     * -> In Procedure 1, we are passing the json doc and a json field to getJsonStream()
     * -> Then we are applying flatMap() operator to go into the menuSections in the json file
     * -> And we are filtering to check the sectionName is null or not
     * -> findAny() helps to find sectionName = null
     * -> And finally returns the boolean value true or false by the terminal operator: isPresent();
     */
    @Test
    @DisplayName("Find Menu SectionName Is Null Procedure 1")
    public void findMenuSectionNameIsNullProcedure1() throws FileNotFoundException {
        
        boolean areAllActorsNullFound = getJsonStream(retrieveMoviesServiceDoc(), "payload.movies")
                .flatMap(menuID -> getJsonStream(menuID, "cast"))
                .filter(this::areAllActorsNull)
                .findAny()
                .isPresent();
        
        if (areAllActorsNullFound) {
            fail();
        }
    }
    
    /**
     * Procedure 2: Test if the sectionName is null
     * If the sectionName == null, then the test FAILS
     * If the sectionName != null then the test PASSES
     * <p>
     * -> In Procedure 2, we are passing the json doc and a json field to getJsonStream()
     * -> Then we are applying forEach() operator to iterate each and every menuSections
     * -> If menuSections = null then peek() operator prints out menuSections = null
     * -> Then we are using the terminal operator: count() to get total count of menuSections which are null
     */
    @Test
    @DisplayName("Find Menu SectionName Is Null Procedure 2")
    public void findMenuSectionNameIsNullProcedure2() throws FileNotFoundException {
        
        AtomicBoolean areActorsNull = new AtomicBoolean(false);
        
        getJsonStream(retrieveMoviesServiceDoc(), MOVIES)
                .forEach(menuID -> {
                    
                    long actorsCount = getJsonStream(menuID, "cast")
                            .filter(this::areAllActorsNull)
                            .peek(menuSection -> log("All actors are null for title: %s", getJsonString(menuID, TITLE)))
                            .count();
                    
                    if (actorsCount > 0) {
                        areActorsNull.set(true);
                    }
                });
        
        if (areActorsNull.get()) {
            fail();
        }
    }
    
    @Step("check all actors are null and return a boolean expression")
    private boolean areAllActorsNull(JsonElement menuSection) {
        return isFieldUndefined(menuSection, "actor1") ||
                getJsonString(menuSection, "actor1") == null &&
                        isFieldUndefined(menuSection, "actor2") ||
                getJsonString(menuSection, "actor2") == null &&
                        isFieldUndefined(menuSection, "actor3") ||
                getJsonString(menuSection, "actor3") == null;
    }
    
    /**
     * The Test case validates the 'fileReference' is null/empty in dispatcher
     * -> If the 'media' field is missing, the test case logs for which venueCode it is missing and Fails
     * -> If the 'fileReference' is null/empty, the test case logs for which venueCode it is missing and Fails
     */
    @Test
    @DisplayName("Test FileReference Is Null Or Empty")
    public void testFileReferenceIsNullOrEmpty() throws FileNotFoundException {
        
        AtomicInteger invalidFileReferenceCount = new AtomicInteger();
        
        getJsonStream(retrieveMoviesServiceDoc(), MOVIES)
                .forEach(venueArray -> {
                    
                    String venueCode = getJsonString(venueArray, EMPTY_STRING);
                    
                    try {
                        getJsonStream(venueArray, EMPTY_STRING)
                                .forEach(media -> {
                                    if (isFileReferenceNullInDispatcher(media)) {
                                        log("fileReference is null/empty for venueCode: {} in dispatcher", venueCode);
                                        invalidFileReferenceCount.incrementAndGet();
                                    }
                                });
                    } catch (Exception e) {
                        log("Exception: %s" + e);
                        log("media field is missing for venueCode: {} in dispatcher", venueCode);
                    }
                });
        
        if (invalidFileReferenceCount.get() > 0) {
            Assertions.fail();
        }
    }
    
    @Step("Returns true if the 'fileReference' is null/empty, otherwise returns false.")
    private boolean isFileReferenceNullInDispatcher(JsonElement media) {
        return isFieldUndefined(media, EMPTY_STRING) ||
                isBlank(getJsonString(media, EMPTY_STRING));
    }
}
