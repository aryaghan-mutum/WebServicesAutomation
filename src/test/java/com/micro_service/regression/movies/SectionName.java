package com.micro_service.regression.movies;

import base.SuperClass;
import com.google.gson.JsonElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static com.micro_service.workflows.ConstantsWorkflow.MOVIES;
import static com.micro_service.workflows.ConstantsWorkflow.TITLE;
import static com.micro_service.workflows.JsonPayloadWorkflow.retrieveMoviesServiceDoc;
import static com.micro_service.workflows.JsonWorkflow.getJsonStream;
import static com.micro_service.workflows.JsonWorkflow.getJsonString;
import static com.micro_service.workflows.JsonWorkflow.isUndefined;
import static org.junit.Assert.fail;

public class SectionName extends SuperClass {
    
    
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
    
    private boolean areAllActorsNull(JsonElement menuSection) {
        return isUndefined(menuSection, "actor1") ||
                getJsonString(menuSection, "actor1") == null &&
                        isUndefined(menuSection, "actor2") ||
                getJsonString(menuSection, "actor2") == null &&
                        isUndefined(menuSection, "actor3") ||
                getJsonString(menuSection, "actor3") == null;
    }
    
    /**
     * The Test case validates the 'fileReference' is null/empty in dispatcher
     * -> If the 'media' field is missing, the test case logs for which venueCode it is missing and Fails
     * -> If the 'fileReference' is null/empty, the test case logs for which venueCode it is missing and Fails
     */
    @Test
    public void testFileReferenceIsNullOrEmptyInVenueDispatcher() throws FileNotFoundException {
        
        AtomicInteger invalidFileReferenceCount = new AtomicInteger();
        
        getJsonStream(retrieveMoviesServiceDoc(), MOVIES)
                .forEach(venueArray -> {
                    
                    String venueCode = getJsonString(venueArray, "");
                    
                    try {
                        getJsonStream(venueArray, "")
                                .forEach(media -> {
                                    if (isFileReferenceNullInDispatcher(media)) {
                                        log("fileReference is null/empty for venueCode: {} in dispatcher", venueCode);
                                        invalidFileReferenceCount.incrementAndGet();
                                    }
                                });
                    } catch (Exception e) {
                        log("media field is missing for venueCode: {} in dispatcher", venueCode);
                    }
                });
        
        if (invalidFileReferenceCount.get() > 0) {
            Assertions.fail();
        }
    }
    
    /**
     * Returns true if the 'fileReference' is null/empty, otherwise returns false.
     */
    private boolean isFileReferenceNullInDispatcher(JsonElement media) {
        return isUndefined(media, "") ||
                StringUtils.isBlank(getJsonString(media, ""));
    }
}
