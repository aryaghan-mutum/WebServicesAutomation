package com.micro_service.regression.countries;

import base.SuperClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.micro_service.workflows.ConstantsWorkflow.COUNTRIES;
import static com.micro_service.workflows.ConstantsWorkflow.COUNTRY;
import static com.micro_service.workflows.JsonPayloadWorkflow.retrieveCountryByPopulationDensityServiceDoc;
import static com.micro_service.workflows.JsonWorkflow.getJsonStream;
import static com.micro_service.workflows.JsonWorkflow.getJsonString;
import static com.micro_service.workflows.Util.isDensityNull;

public class TestNullValuesForCountryNamesAndDensity extends SuperClass {
    
    private static final int TOTAL_COUNTRIES_WITH_POPULATION_DENSITY_AS_NOT_NULL = 175;
    
    /**
     * 1. Get a list of countries
     * 2. if size of the countriesList = 0 OR countriesList doesn't contain "Cube" then FAIL
     */
    @Test
    public void testCountriesList() throws FileNotFoundException {
        
        List<String> countriesList =
                getJsonStream(retrieveCountryByPopulationDensityServiceDoc(), COUNTRIES)
                        .map(country -> getJsonString(country, COUNTRY))
                        .collect(Collectors.toList());
        
        if (countriesList.size() == 0 || !countriesList.contains("Cuba")) {
            Assertions.fail();
        }
        
    }
    
    /**
     * 1. Get a list of countries that doesn't have density as null
     * 2. Asserts the count with density as not null
     */
    @Test
    public void testCountryNamesWithPopulationDensityAsNotNull() throws FileNotFoundException {
        
        List<String> countryNamesWithHeightNotNullList = new ArrayList<>();
        
        getJsonStream(retrieveCountryByPopulationDensityServiceDoc(), COUNTRIES)
                .forEach(country -> {
                    
                    String countryName = getJsonString(country, COUNTRY);
                    
                    if (!isDensityNull(country)) {
                        countryNamesWithHeightNotNullList.add(countryName);
                    }
                });
        
        Assertions.assertEquals(countryNamesWithHeightNotNullList.stream().count(), TOTAL_COUNTRIES_WITH_POPULATION_DENSITY_AS_NOT_NULL);
    }
    
}
