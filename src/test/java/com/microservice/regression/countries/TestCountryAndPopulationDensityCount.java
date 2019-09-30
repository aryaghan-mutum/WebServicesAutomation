package com.microservice.regression.countries;

import base.SuperClass;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static com.microservice.workflows.ConstantsWorkflow.COUNTRIES;
import static com.microservice.workflows.JsonPayloadWorkflow.retrieveCountryByPopulationDensityServiceDoc;
import static com.microservice.workflows.JsonWorkflow.getJsonStream;
import static com.microservice.workflows.Util.isDensityNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Anurag Muthyam
 */

public class TestCountryAndPopulationDensityCount extends SuperClass {
    
    private static final int TOTAL_COUNTRIES_WITH_POPULATION_DENSITY_AS_NULL = 243;
    private static final int TOTAL_COUNTRIES_WITH_POPULATION_DENSITY_AS_NOT_NULL = 175;
    
    /**
     * 1. Gets total count of the countries and performs assertion
     * 2. Asserts the count
     */
    @Test
    public void testTotalCountriesCountWithDensityANull() throws FileNotFoundException {
        int totalCountriesCount = (int) getJsonStream(retrieveCountryByPopulationDensityServiceDoc(), COUNTRIES).count();
        
        assertEquals(totalCountriesCount, TOTAL_COUNTRIES_WITH_POPULATION_DENSITY_AS_NULL);
    }
    
    /**
     * 1. Gets total count of the countries with density as not null, and performs assertion
     * 2. Asserts the count with density as not null
     */
    @Test
    public void testTotalCountriesCountWithDensityAsNotNull() throws FileNotFoundException {
        
        int totalCountriesCountWithDensityAsNotNull =
                (int) getJsonStream(retrieveCountryByPopulationDensityServiceDoc(), COUNTRIES)
                        .filter(country -> !isDensityNull(country))
                        .count();
        
        assertEquals(totalCountriesCountWithDensityAsNotNull, TOTAL_COUNTRIES_WITH_POPULATION_DENSITY_AS_NOT_NULL);
    }
    
}
