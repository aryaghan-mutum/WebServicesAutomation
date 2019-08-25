package com.micro_service.regression.countries;

import base.SuperClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static com.micro_service.workflows.ConstantsWorkflow.COUNTRIES;
import static com.micro_service.workflows.JsonPayloadWorkflow.retrieveCountryByPopulationDensityServiceDoc;
import static com.micro_service.workflows.JsonWorkflow.getJsonStream;
import static com.micro_service.workflows.Util.isDensityNull;

public class TestCountryAndPopulationDensityCount extends SuperClass {
    
    private static final int TOTAL_COUNTRIES_WITH_POPULATION_DENSITY_AS_NULL = 243;
    private static final int TOTAL_COUNTRIES_WITH_POPULATION_DENSITY_AS_NOT_NULL = 175;
    
    /**
     * Gets total count of the countries and performs assertion
     */
    @Test
    public void testTotalCountriesCountWithDensityANull() throws FileNotFoundException {
        int totalCountriesCount = (int) getJsonStream(retrieveCountryByPopulationDensityServiceDoc(), COUNTRIES).count();
        
        Assertions.assertEquals(totalCountriesCount, TOTAL_COUNTRIES_WITH_POPULATION_DENSITY_AS_NULL);
    }
    
    /**
     * Gets total count of the countries with density as not null, and performs assertion
     */
    @Test
    public void testTotalCountriesCountWithDensityAsNotNull() throws FileNotFoundException {
        
        int totalCountriesCountWithHeightsAsNotNull =
                (int) getJsonStream(retrieveCountryByPopulationDensityServiceDoc(), COUNTRIES)
                        .filter(country -> !isDensityNull(country))
                        .count();
        
        Assertions.assertEquals(totalCountriesCountWithHeightsAsNotNull, TOTAL_COUNTRIES_WITH_POPULATION_DENSITY_AS_NOT_NULL);
    }
    
}
