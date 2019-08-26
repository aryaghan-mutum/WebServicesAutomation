package com.micro_service.regression.movies;

import base.SuperClass;
import com.google.gson.JsonElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import static com.micro_service.workflows.ConstantsWorkflow.MOVIES;
import static com.micro_service.workflows.ConstantsWorkflow.TITLE;
import static com.micro_service.workflows.JsonPayloadWorkflow.retrieveMoviesServiceDoc;
import static com.micro_service.workflows.JsonWorkflow.getJsonStream;
import static com.micro_service.workflows.JsonWorkflow.getJsonString;
import static com.micro_service.workflows.JsonWorkflow.isUndefined;

public class SectionName extends SuperClass {
    
    @Test
    public void func() {
        String string = "foobar:foo:bar"
                .chars()
                .distinct()
                .mapToObj(c -> String.valueOf((char) c))
                .sorted()
                .collect(Collectors.joining());
        System.out.println(string);
    }
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
            Assertions.fail();
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
            Assertions.fail();
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
}
