package com.micro_service.regression.countries;

import base.SuperClass;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.micro_service.workflows.ConstantsWorkflow.COUNTRIES;
import static com.micro_service.workflows.ConstantsWorkflow.COUNTRY;
import static com.micro_service.workflows.JsonPayloadWorkflow.retrieveCountryByContinentServiceDoc;
import static com.micro_service.workflows.JsonPayloadWorkflow.retrieveCountryByPopulationDensityServiceDoc;
import static com.micro_service.workflows.JsonWorkflow.getJsonStream;
import static com.micro_service.workflows.JsonWorkflow.getJsonString;
import static com.micro_service.workflows.Util.isContinentNull;

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
    public void testCountriesTotalCountThatStartsWithLetterZUsingCount() throws FileNotFoundException {
        
        long countryNameCountThatStartsWithLetterZ =
                getJsonStream(retrieveCountryByPopulationDensityServiceDoc(), COUNTRIES)
                        .map(country -> getJsonString(country, COUNTRY))
                        .filter(country -> country.startsWith("Z"))
                        .count();
        
        Assertions.assertEquals(countryNameCountThatStartsWithLetterZ, 2);
    }
    
    /**
     * Operations used: map() and anyMatch()
     * 1. Map the country names
     * 2. Check If the country name starts with the letter 'Z'
     * 3. Assert the boolean
     */
    @Test
    public void testCountriesThatStartsWithLetterUsingAnyMatch() throws FileNotFoundException {
        
        boolean isCountryNameStartsWithLetterZ =
                getJsonStream(retrieveCountryByPopulationDensityServiceDoc(), COUNTRIES)
                        .map(country -> getJsonString(country, COUNTRY))
                        .anyMatch(flower -> flower.startsWith("Z"));
        
        
        Assertions.assertEquals(isCountryNameStartsWithLetterZ, true);
    }
    
    /**
     * Operations used: map(), filter() and collect()
     * 1. Map the country names
     * 2. If the country name starts with the letter 'Z' then collect the countries in a list
     * 3. Assert the List which has countryName that starts with letter 'Z'
     */
    @Test
    public void testCountriesListThatStartsWithLetterZProcedure1() throws FileNotFoundException {
        
        List<String> countryNameThatStartsWithLetterZList =
                getJsonStream(retrieveCountryByPopulationDensityServiceDoc(), COUNTRIES)
                        .map(country -> getJsonString(country, COUNTRY))
                        .filter(country -> country.startsWith("Z"))
                        .collect(Collectors.toList());
        
        Assertions.assertEquals(countryNameThatStartsWithLetterZList.toString(), "[Zambia, Zimbabwe]");
    }
    
    /**
     * Using Collectors.joining(", ")):
     * Operations used: filter(), map(), filter(), map() and collect()
     */
    @Test
    public void testCountriesListThatStartsWithLetterZProcedure2() throws FileNotFoundException {
        
        String countriesStartsWithLetterZ =
                getJsonStream(retrieveCountryByContinentServiceDoc(), COUNTRIES)
                        .filter(country -> !isContinentNull(country))
                        .map(country -> getJsonString(country, COUNTRY))
                        .filter(country -> country.startsWith("Z"))
                        .map(String::toUpperCase)
                        .collect(Collectors.joining(", "));
        
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
    public void testCountriesListThatStartsWithLetterZProcedure3() throws FileNotFoundException {
        
        List<String> countriesStartsWithLetterZList =
                getJsonStream(retrieveCountryByContinentServiceDoc(), COUNTRIES)
                        .filter(country -> !isContinentNull(country))
                        .map(country -> getJsonString(country, COUNTRY))
                        .map(String::toUpperCase)
                        .filter(country -> country.startsWith("Z"))
                        .collect(Collectors.toList());
        
        String countriesStartsWithLetterZWithComma =
                countriesStartsWithLetterZList
                        .stream()
                        .collect(Collectors.joining(", "));
        
        Assert.assertEquals(countriesStartsWithLetterZWithComma, "zambia, zimbabwe".toUpperCase());
        
        String countriesStartsWithLetterZWithHiphen =
                Pattern.compile(", ")
                        .splitAsStream(countriesStartsWithLetterZWithComma)
                        .collect(Collectors.joining("-"));
        
        Assert.assertEquals(countriesStartsWithLetterZWithHiphen, "zambia-zimbabwe".toUpperCase());
    }
    
    /**
     * Operations used: map(), filter(), reduce(), get(), chars(), distinct(), maoToObj(), sorted() and collect()
     */
    @Test
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
                .collect(Collectors.joining(" "));
        
        Assert.assertEquals(countriesStartsWithLetterZStr1, "  Z a b d e i m n w");
    }
    
    
}
