package com.microservice.regression.countries;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static com.microservice.workflows.ConstantsWorkflow.COUNTRIES;
import static com.microservice.workflows.JsonPayloadWorkflow.retrieveCountryByPopulationDensityServiceDoc;
import static com.microservice.workflows.JsonWorkflow.getJsonStream;
import static com.microservice.workflows.Util.isDensityNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Anurag Muthyam
 * url: https://github.com/aryaghan-mutum
 */

public class TestCountryAndPopulationDensityCount {
    
    private static final int TOTAL_COUNTRIES_WITH_POPULATION_DENSITY_AS_NULL = 243;
    private static final int TOTAL_COUNTRIES_WITH_POPULATION_DENSITY_AS_NOT_NULL = 175;
    
    /**
     * 1. Gets total count of the countries and performs assertion
     * 2. Asserts the count
     */
    @Test
    @DisplayName("Test Total Countries Count With Density As Null")
    public void testTotalCountriesCountWithDensityAsNull() throws FileNotFoundException {
        int totalCountriesCount = (int) getJsonStream(retrieveCountryByPopulationDensityServiceDoc(), COUNTRIES).count();
        
        assertEquals(totalCountriesCount, TOTAL_COUNTRIES_WITH_POPULATION_DENSITY_AS_NULL);
    }
    
    /**
     * 1. Gets total count of the countries with density as not null, and performs assertion
     * 2. Asserts the count with density as not null
     */
    @Test
    @DisplayName("Test Total Countries Count With Density As Not Null")
    public void testTotalCountriesCountWithDensityAsNotNull() throws FileNotFoundException {
        
        int totalCountriesCountWithDensityAsNotNull =
                (int) getJsonStream(retrieveCountryByPopulationDensityServiceDoc(), COUNTRIES)
                        .filter(country -> !isDensityNull(country))
                        .count();
        
        assertEquals(totalCountriesCountWithDensityAsNotNull, TOTAL_COUNTRIES_WITH_POPULATION_DENSITY_AS_NOT_NULL);
    }
    
}
