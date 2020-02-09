package com.microservice.regression.countries;

import base.SuperClass;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.microservice.workflows.ConstantsWorkflow.COUNTRIES;
import static com.microservice.workflows.ConstantsWorkflow.COUNTRY;
import static com.microservice.workflows.JsonPayloadWorkflow.retrieveCountryByContinentServiceDoc;
import static com.microservice.workflows.JsonPayloadWorkflow.retrieveCountryByPopulationDensityServiceDoc;
import static com.microservice.workflows.JsonWorkflow.getJsonStream;
import static com.microservice.workflows.JsonWorkflow.getJsonString;
import static com.microservice.workflows.Util.isContinentNull;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Anurag Muthyam
 */

public class TestCountryNameStartsWithLetterZ extends SuperClass {
    
    /**
     * Operations used: map(), filter() and count()
     * 1. Map the country names
     * 2. If the country name starts with the letter 'Z' then get the total count
     * 3. Assert the count
     */
    @Test
    @DisplayName("Test Countries Total Count That Starts With Letter Z Using Count")
    public void testCountriesTotalCountThatStartsWithLetterZUsingCount() throws FileNotFoundException {
        
        long countryNameCountThatStartsWithLetterZ =
                getJsonStream(retrieveCountryByPopulationDensityServiceDoc(), COUNTRIES)
                        .map(country -> getJsonString(country, COUNTRY))
                        .filter(country -> country.startsWith("Z"))
                        .count();
        
        assertEquals(countryNameCountThatStartsWithLetterZ, 2);
    }
    
    /**
     * Operations used: map() and anyMatch()
     * 1. Map the country names
     * 2. Check If the country name starts with the letter 'Z'
     * 3. Assert the boolean
     */
    @Test
    @DisplayName("Test Countries That Starts With Letter Using AnyMatch")
    public void testCountriesThatStartsWithLetterUsingAnyMatch() throws FileNotFoundException {
        
        boolean isCountryNameStartsWithLetterZ =
                getJsonStream(retrieveCountryByPopulationDensityServiceDoc(), COUNTRIES)
                        .map(country -> getJsonString(country, COUNTRY))
                        .anyMatch(flower -> flower.startsWith("Z"));
        
        
        assertEquals(isCountryNameStartsWithLetterZ, true);
    }
    
    /**
     * Operations used: map(), filter() and collect()
     * 1. Map the country names
     * 2. If the country name starts with the letter 'Z' then collect the countries in a list
     * 3. Assert the List which has countryName that starts with letter 'Z'
     */
    @Test
    @DisplayName("Test Countries List That Starts With Letter Z Procedure 1")
    public void testCountriesListThatStartsWithLetterZProcedure1() throws FileNotFoundException {
        
        List<String> countryNameThatStartsWithLetterZList =
                getJsonStream(retrieveCountryByPopulationDensityServiceDoc(), COUNTRIES)
                        .map(country -> getJsonString(country, COUNTRY))
                        .filter(country -> country.startsWith("Z"))
                        .collect(toList());
        
        assertEquals(countryNameThatStartsWithLetterZList.toString(), "[Zambia, Zimbabwe]");
    }
    
    /**
     * Using Collectors.joining(", ")):
     * Operations used: filter(), map(), filter(), map() and collect()
     */
    @Test
    @DisplayName("Test Countries List That Starts With Letter Z Procedure 2")
    public void testCountriesListThatStartsWithLetterZProcedure2() throws FileNotFoundException {
        
        String countriesStartsWithLetterZ =
                getJsonStream(retrieveCountryByContinentServiceDoc(), COUNTRIES)
                        .filter(country -> !isContinentNull(country))
                        .map(country -> getJsonString(country, COUNTRY))
                        .filter(country -> country.startsWith("Z"))
                        .map(String::toUpperCase)
                        .collect(joining(", "));
        
        Assert.assertEquals(countriesStartsWithLetterZ, "zambia, zimbabwe".toUpperCase());
    }
    
    /**
     * Operations used: filter(), map(), filter(), map() and collect():
     * 1. Map the country names
     * 2. If the country name starts with the letter 'Z' then collect the countries in a list
     * 3. Convert the list to a String with a delimiter: ", "
     * 4. Assert the Countries as type String
     * 5. Convert the list to a String with a delimiter: "-"
     * 6. Assert the Countries as type String
     */
    @Test
    @DisplayName("Test Countries List That Starts With Letter Z Procedure 3")
    public void testCountriesListThatStartsWithLetterZProcedure3() throws FileNotFoundException {
        
        List<String> countriesStartsWithLetterZList =
                getJsonStream(retrieveCountryByContinentServiceDoc(), COUNTRIES)
                        .filter(country -> !isContinentNull(country))
                        .map(country -> getJsonString(country, COUNTRY))
                        .map(String::toUpperCase)
                        .filter(country -> country.startsWith("Z"))
                        .collect(toList());
        
        String countriesStartsWithLetterZWithComma =
                countriesStartsWithLetterZList
                        .stream()
                        .collect(joining(", "));
        
        Assert.assertEquals(countriesStartsWithLetterZWithComma, "zambia, zimbabwe".toUpperCase());
        
        String countriesStartsWithLetterZWithHiphen =
                Pattern.compile(", ")
                        .splitAsStream(countriesStartsWithLetterZWithComma)
                        .collect(joining("-"));
        
        Assert.assertEquals(countriesStartsWithLetterZWithHiphen, "zambia-zimbabwe".toUpperCase());
    }
    
    /**
     * Operations used: map(), filter(), reduce(), get(), chars(), distinct(), maoToObj(), sorted() and collect()
     */
    @Test
    @DisplayName("Test Countries Start With Letter Z And Do String Manipulation")
    public void testCountriesStartWithLetterZAndDoStringManipulation() throws FileNotFoundException {
        
        String countriesStartsWithLetterZStr1 = getJsonStream(retrieveCountryByPopulationDensityServiceDoc(), COUNTRIES)
                .map(country -> getJsonString(country, COUNTRY))
                .filter(country -> country.startsWith("Z"))
                .reduce((a, b) -> a + " and " + b)
                .get()
                .chars()
                .distinct()
                .mapToObj(c -> String.valueOf((char) c))
                .sorted()
                .collect(joining(" "));
        
        Assert.assertEquals(countriesStartsWithLetterZStr1, "  Z a b d e i m n w");
    }
    
    
}
