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
     */
    @Test
    public void findMenuSectionNameIsNullProcedure2() throws FileNotFoundException {
        
        AtomicBoolean bFailed = new AtomicBoolean(false);
        
        getJsonStream(retrieveMenuServiceDoc(), "payload.menus")
                .forEach(menuID -> {
                    
                    long sectionNameCount = getJsonStream(menuID, "menu.menuSections")
                            .filter(this::isMenuSectionNameNull)
                            .peek(menuSection -> log("sectionName is: %s for menuName: %s",
                                    getJsonString(menuSection, "sectionName"),
                                    getJsonString(menuID, "menu.menuName")))
                            .count();
                    
                    if (sectionNameCount > 0) {
                        bFailed.set(true);
                    }
                });
        
        if (bFailed.get()) {
            Assertions.fail();
        }
    }
    
    private boolean isMenuSectionNameNull(JsonElement menuSection) {
        return isUndefined(menuSection, "sectionName") || getJsonString(menuSection, "sectionName") == null;
    }
}
