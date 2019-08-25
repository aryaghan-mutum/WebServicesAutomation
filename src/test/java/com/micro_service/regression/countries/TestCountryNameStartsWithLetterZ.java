package com.micro_service.regression.countries;

import base.SuperClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static com.micro_service.workflows.ConstantsWorkflow.COUNTRIES;
import static com.micro_service.workflows.ConstantsWorkflow.COUNTRY;
import static com.micro_service.workflows.JsonPayloadWorkflow.retrieveCountryByPopulationDensityServiceDoc;
import static com.micro_service.workflows.JsonWorkflow.getJsonStream;
import static com.micro_service.workflows.JsonWorkflow.getJsonString;

public class TestCountryNameStartsWithLetterZ extends SuperClass {
    
    /**
     * Operations used: map(), filter() and count()
     * 1. Map the country names
     * 2. If the country name starts with the letter 'Z' then get the total count
     * 3. Assert the count
     */
    @Test
    public void testCountriesTotalCountThatStartsWithLetterZ() throws FileNotFoundException {
        
        long countryNameCountThatStartsWithLetterZ =
                getJsonStream(retrieveCountryByPopulationDensityServiceDoc(), COUNTRIES)
                        .map(country -> getJsonString(country, COUNTRY))
                        .filter(country -> country.startsWith("Z"))
                        .count();
        
        Assertions.assertEquals(countryNameCountThatStartsWithLetterZ, 2);
    }
    
    /**
     * Operations used: map(), filter() and collect()
     * 1. Map the country names
     * 2. If the country name starts with the letter 'Z' then collect the countries in a list
     * 3. Assert the List which has countryName that starts with letter 'Z'
     */
    @Test
    public void testCountriesListThatStartsWithLetterZ() throws FileNotFoundException {
        
        List<String> countryNameThatStartsWithLetterZList =
                getJsonStream(retrieveCountryByPopulationDensityServiceDoc(), COUNTRIES)
                        .map(country -> getJsonString(country, COUNTRY))
                        .filter(country -> country.startsWith("Z"))
                        .collect(Collectors.toList());
        
        Assertions.assertEquals(countryNameThatStartsWithLetterZList.toString(), "[Zambia, Zimbabwe]");
        
    }
    
}
