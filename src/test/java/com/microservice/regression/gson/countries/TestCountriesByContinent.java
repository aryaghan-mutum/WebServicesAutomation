package com.microservice.regression.gson.countries;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.microservice.workflows.ConstantsWorkflow.CONTINENT;
import static com.microservice.workflows.ConstantsWorkflow.COUNTRIES;
import static com.microservice.workflows.ConstantsWorkflow.COUNTRY;
import static com.microservice.workflows.JsonPayloadWorkflow.retrieveCountryByContinentServiceDoc;
import static com.microservice.workflows.JsonWorkflow.getJsonStream;
import static com.microservice.workflows.JsonWorkflow.getJsonString;
import static com.microservice.workflows.Util.isContinentNull;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Anurag Muthyam
 * url: https://github.com/aryaghan-mutum
 */

@Slf4j
public class TestCountriesByContinent {
    
    /**
     * 1. Exclude a list of countries who's continent is null by using filter()
     * 2. Get countryName and continentName
     * 3. Collect a list of countries for all 6 continents
     * 4. Assert the total count of countries for each continent
     */
    @Test
    @DisplayName("Test Countries Count For Al Continents")
    public void testCountriesCountForAlContinents() throws FileNotFoundException {
        
        List<String> asiaCountriesList = new ArrayList<>();
        List<String> europeCountriesList = new ArrayList<>();
        List<String> oceaniaCountriesList = new ArrayList<>();
        List<String> africaCountriesList = new ArrayList<>();
        List<String> northAmericaCountriesList = new ArrayList<>();
        List<String> southAmericaCountriesList = new ArrayList<>();
        
        getJsonStream(retrieveCountryByContinentServiceDoc(), COUNTRIES)
                .filter(country -> !isContinentNull(country))
                .forEach(country -> {
                    
                    String countryName = getJsonString(country, COUNTRY);
                    String continentName = getJsonString(country, CONTINENT);
                    
                    if (continentName.equalsIgnoreCase("asia")) {
                        asiaCountriesList.add(countryName);
                    }
                    
                    if (continentName.equalsIgnoreCase("europe")) {
                        europeCountriesList.add(countryName);
                    }
                    
                    if (continentName.equalsIgnoreCase("oceania")) {
                        oceaniaCountriesList.add(countryName);
                    }
                    
                    if (continentName.equalsIgnoreCase("africa")) {
                        africaCountriesList.add(countryName);
                    }
                    
                    if (continentName.equalsIgnoreCase("north america")) {
                        northAmericaCountriesList.add(countryName);
                    }
                    
                    if (continentName.equalsIgnoreCase("south america")) {
                        southAmericaCountriesList.add(countryName);
                    }
                });
        
        int totalCountries =
                asiaCountriesList.size() + europeCountriesList.size() + oceaniaCountriesList.size() +
                        africaCountriesList.size() + northAmericaCountriesList.size() + southAmericaCountriesList.size();
        
        log.info("Total Countries: {} ", totalCountries);
        assertEquals(totalCountries, 228);
        
        assertEquals(asiaCountriesList.size(), 48);
        assertEquals(europeCountriesList.size(), 46);
        assertEquals(oceaniaCountriesList.size(), 27);
        assertEquals(africaCountriesList.size(), 58);
        assertEquals(northAmericaCountriesList.size(), 35);
        assertEquals(southAmericaCountriesList.size(), 14);
    }
    
    /**
     * Operations used: filer(), map(), distinct(), sorted() and collect()
     * 1. Exclude a list of countries who's continent is null by using filter()
     * 2. Get all the unique continentName and sort and store them in a list
     * 3. Assert: If the list is empty then the test FAILS
     * 4. Assert: Compare two lists are equal
     */
    @Test
    @Step("Get A List Of Continents And Test If It Is Empty")
    public void getAListOfContinentsAndTestIfItIsEmpty() throws FileNotFoundException {
        
        List<String> expectedContinentsList = getJsonStream(retrieveCountryByContinentServiceDoc(), COUNTRIES)
                .filter(country -> !isContinentNull(country))
                .map(country -> getJsonString(country, CONTINENT))
                .distinct()
                .sorted()
                .collect(toList());
        
        if (expectedContinentsList.isEmpty()) {
            Assertions.fail();
        }
        
        List<String> actualContinentsList2 =
                Stream.of("Africa", "Asia", "Europe", "Oceania", "Antarctica", "North America", "South America")
                        .sorted()
                        .collect(toList());
        
        Assert.assertEquals(actualContinentsList2, expectedContinentsList);
    }
    
    
}
