package com.micro_service.regression.menus;

import base.SuperClass;
import com.google.gson.JsonElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static com.micro_service.workflows.JsonPayloadWorkflow.retrieveMenuServiceDoc;
import static com.micro_service.workflows.JsonWorkflow.getJsonStream;
import static com.micro_service.workflows.JsonWorkflow.getJsonString;
import static com.micro_service.workflows.JsonWorkflow.isUndefined;

public class MenuName extends SuperClass {
    
    /**
     * Procedure 1: Test if the menuName is null
     * If the menuName == null, then the test FAILS
     * If the menuName != null then the test PASSES
     */
    @Test
    public void findMenuNameIsNullProcedure1() throws FileNotFoundException {
        
        boolean isMenuNameNullFound = getJsonStream(retrieveMenuServiceDoc(), "payload.menus")
                .filter(this::isMenuNameNullForProcedure1)
                .findAny()
                .isPresent();
        
        if (isMenuNameNullFound) {
            Assertions.fail();
        }
    }
    
    private boolean isMenuNameNullForProcedure1(JsonElement menu) {
        try {
            log("menuName is null for menuID: %s", getJsonString(menu, "menuID"));
            
            return getJsonString(menu, "menu.menuName") == null;
        } catch (Exception ex) {
            log("jsonString method throws exception when the tag is not present, make sure that is also treated as null");
            
            return true;
        }
    }
    
    /**
     * Procedure 2: Test if the menuName is null
     * If the menuName == null, then the test FAILS
     * If the menuName != null then the test PASSES
     */
    @Test
    public void findMenuNameIsNullProcedure2() throws FileNotFoundException {
        
        long menuNameCount = getJsonStream(retrieveMenuServiceDoc(), "payload.menus")
                .filter(this::isMenuNameNullForProcedure2)
                .peek(menu -> log("menuName is null for menuID: %s", getJsonString(menu, "menuID")))
                .count();
        
        if (menuNameCount > 0) {
            Assertions.fail();
        }
    }
    
    private boolean isMenuNameNullForProcedure2(JsonElement menu) {
        return isUndefined(menu, "menu.menuName") || getJsonString(menu, "menu.menuName") == null;
    }
    
    
}
