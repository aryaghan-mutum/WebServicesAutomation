package com.micro_service.regression.countries;

import base.SuperClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static com.micro_service.workflows.JsonPayloadWorkflow.retrieveCountryByAvgMaleHeightServiceDoc;
import static com.micro_service.workflows.JsonWorkflow.getJsonStream;
import static com.micro_service.workflows.Util.isHeightNull;

public class TestCountryAndHeightCount extends SuperClass {
    
    @Test
    public void testTotalCountriesCount() throws FileNotFoundException {
        int totalCountriesCount = (int) getJsonStream(retrieveCountryByAvgMaleHeightServiceDoc(), "payload.countries").count();
        
        Assertions.assertEquals(totalCountriesCount, 243);
    }
    
    @Test
    public void testTotalCountriesCountWithHeightsAsNotNull() throws FileNotFoundException {
        
        int totalCountriesCountWithHeightsAsNotNull =
                (int) getJsonStream(retrieveCountryByAvgMaleHeightServiceDoc(), "payload.countries")
                        .filter(country -> !isHeightNull(country))
                        .count();
        
        Assertions.assertEquals(totalCountriesCountWithHeightsAsNotNull, 78);
    }
    
}
