package com.micro_service.regression.menus;

import base.SuperClass;
import com.google.gson.JsonElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.micro_service.workflows.JsonPayloadWorkflow.retrieveMenuServiceDoc;
import static com.micro_service.workflows.JsonWorkflow.getJsonStream;
import static com.micro_service.workflows.JsonWorkflow.getJsonString;
import static com.micro_service.workflows.JsonWorkflow.isUndefined;

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
        
        boolean isSectionNameNullFound = getJsonStream(retrieveMenuServiceDoc(), "payload.menus")
                .flatMap(menuID -> getJsonStream(menuID, "menu.menuSections"))
                .filter(this::isMenuSectionNameNull)
                .findAny()
                .isPresent();
        
        if (isSectionNameNullFound) {
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
        
        AtomicBoolean isSectionNameNull = new AtomicBoolean(false);
        
        getJsonStream(retrieveMenuServiceDoc(), "payload.menus")
                .forEach(menuID -> {
                    
                    long sectionNameCount = getJsonStream(menuID, "menu.menuSections")
                            .filter(this::isMenuSectionNameNull)
                            .peek(menuSection -> log("sectionName is: %s for menuName: %s",
                                    getJsonString(menuSection, "sectionName"),
                                    getJsonString(menuID, "menu.menuName")))
                            .count();
                    
                    if (sectionNameCount > 0) {
                        isSectionNameNull.set(true);
                    }
                });
        
        if (isSectionNameNull.get()) {
            Assertions.fail();
        }
    }
    
    private boolean isMenuSectionNameNull(JsonElement menuSection) {
        return isUndefined(menuSection, "sectionName") || getJsonString(menuSection, "sectionName") == null;
    }
}
