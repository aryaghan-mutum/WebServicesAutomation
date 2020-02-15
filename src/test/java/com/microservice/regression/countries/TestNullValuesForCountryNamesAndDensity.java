package com.microservice.regression.countries;

import base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.microservice.workflows.ConstantsWorkflow.COUNTRIES;
import static com.microservice.workflows.ConstantsWorkflow.COUNTRY;
import static com.microservice.workflows.JsonPayloadWorkflow.retrieveCountryByPopulationDensityServiceDoc;
import static com.microservice.workflows.JsonWorkflow.getJsonStream;
import static com.microservice.workflows.JsonWorkflow.getJsonString;
import static com.microservice.workflows.Util.isDensityNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Anurag Muthyam
 */

public class TestNullValuesForCountryNamesAndDensity extends BaseTest {
    
    private static final int TOTAL_COUNTRIES_WITH_POPULATION_DENSITY_AS_NOT_NULL = 175;
    
    /**
     * 1. Get a list of countries
     * 2. if size of the countriesList = 0 OR countriesList doesn't contain "Cube" then FAIL
     */
    @Test
    @DisplayName("Test Countries List")
    public void testCountriesList() throws FileNotFoundException {
        
        List<String> countriesList =
                getJsonStream(retrieveCountryByPopulationDensityServiceDoc(), COUNTRIES)
                        .map(country -> getJsonString(country, COUNTRY))
                        .collect(Collectors.toList());
        
        if (countriesList.size() == 0 || !countriesList.contains("Cuba")) {
            fail();
        }
        
    }
    
    /**
     * 1. Get a list of countries that doesn't have density as null
     * 2. Asserts the count with density as not null
     */
    @Test
    @DisplayName("Test Country Names With Population Density As Not Null")
    public void testCountryNamesWithPopulationDensityAsNotNull() throws FileNotFoundException {
        
        List<String> countryNamesWithHeightNotNullList = new ArrayList<>();
        
        getJsonStream(retrieveCountryByPopulationDensityServiceDoc(), COUNTRIES)
                .forEach(country -> {
                    
                    String countryName = getJsonString(country, COUNTRY);
                    
                    if (!isDensityNull(country)) {
                        countryNamesWithHeightNotNullList.add(countryName);
                    }
                });
        
        assertEquals(countryNamesWithHeightNotNullList.stream().count(), TOTAL_COUNTRIES_WITH_POPULATION_DENSITY_AS_NOT_NULL);
    }
    
}
