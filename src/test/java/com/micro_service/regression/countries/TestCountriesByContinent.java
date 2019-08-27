package com.micro_service.regression.countries;

import base.SuperClass;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.micro_service.workflows.ConstantsWorkflow.CONTINENT;
import static com.micro_service.workflows.ConstantsWorkflow.COUNTRIES;
import static com.micro_service.workflows.ConstantsWorkflow.COUNTRY;
import static com.micro_service.workflows.JsonPayloadWorkflow.retrieveCountryByContinentServiceDoc;
import static com.micro_service.workflows.JsonWorkflow.getJsonStream;
import static com.micro_service.workflows.JsonWorkflow.getJsonString;
import static com.micro_service.workflows.Util.isContinentNull;

/**
 * @author Anurag Muthyam
 */

public class TestCountriesByContinent extends SuperClass {
    
    /**
     * 1. Exclude a list of countries who's continent is null by using filter()
     * 2. Get countryName and continentName
     * 3. Collect a list of countries for all 6 continents
     * 4. Assert the total count of countries for each continent
     */
    @Test
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
        
        log("Total Countries: %s ", totalCountries);
        Assertions.assertEquals(totalCountries, 228);
        
        Assertions.assertEquals(asiaCountriesList.size(), 48);
        Assertions.assertEquals(europeCountriesList.size(), 46);
        Assertions.assertEquals(oceaniaCountriesList.size(), 27);
        Assertions.assertEquals(africaCountriesList.size(), 58);
        Assertions.assertEquals(northAmericaCountriesList.size(), 35);
        Assertions.assertEquals(southAmericaCountriesList.size(), 14);
    }
    
    /**
     * Operations used: filer(), map(), distinct(), sorted() and collect()
     * 1. Exclude a list of countries who's continent is null by using filter()
     * 2. Get all the unique continentName and sort and store them in a list
     * 3. Assert: If the list if empty then the test FAILS
     * 4. Assert: Compare two lists are equal
     */
    @Test
    public void getAListOfContinentsAndTestIfItIsEmpty() throws FileNotFoundException {
        
        List<String> expectedContinentsList = getJsonStream(retrieveCountryByContinentServiceDoc(), COUNTRIES)
                .filter(country -> !isContinentNull(country))
                .map(country -> getJsonString(country, CONTINENT))
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        
        if (expectedContinentsList.isEmpty()) {
            Assert.fail();
        }
        
        List<String> actualContinentsList2 =
                Stream.of("Africa", "Asia", "Europe", "Oceania", "Antarctica", "North America", "South America")
                        .sorted()
                        .collect(Collectors.toList());
        
        Assert.assertEquals(actualContinentsList2, expectedContinentsList);
    }
    
    
}
