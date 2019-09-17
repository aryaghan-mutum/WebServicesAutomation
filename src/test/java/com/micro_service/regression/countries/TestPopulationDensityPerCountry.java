package com.micro_service.regression.countries;

import base.SuperClass;
import com.google.gson.JsonElement;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.micro_service.workflows.ConstantsWorkflow.COUNTRIES;
import static com.micro_service.workflows.ConstantsWorkflow.COUNTRY;
import static com.micro_service.workflows.ConstantsWorkflow.DENSITY;
import static com.micro_service.workflows.JsonPayloadWorkflow.retrieveCountryByContinentServiceDoc;
import static com.micro_service.workflows.JsonPayloadWorkflow.retrieveCountryByPopulationDensityServiceDoc;
import static com.micro_service.workflows.JsonWorkflow.getJsonDouble;
import static com.micro_service.workflows.JsonWorkflow.getJsonStream;
import static com.micro_service.workflows.JsonWorkflow.getJsonString;
import static com.micro_service.workflows.Util.isDensityNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Anurag Muthyam
 */

public class TestPopulationDensityPerCountry extends SuperClass {
    
    private static final double LOWEST_POPULATION_DENSITY = 0.0;
    private static final double HIGHEST_POPULATION_DENSITY = 661.96;
    private static final double AVG_POPULATION_DENSITY = 119.67;
    
    /**
     * 1. Gets a stream of countries and remove the ones that has density = null
     * 2. Gets density one after another
     * 3. Gets the min density using min()
     * 4. Assert
     */
    @Test
    public void testLowestPopulationDensity() throws FileNotFoundException {
        
        double lowestPopulationDensity = getJsonStream(retrieveCountryByPopulationDensityServiceDoc(), COUNTRIES)
                .filter(country -> !isDensityNull(country))
                .map(country -> getJsonDouble(country, DENSITY))
                .mapToDouble(Double::doubleValue)
                .min()
                .getAsDouble();
        
        assertEquals(lowestPopulationDensity, LOWEST_POPULATION_DENSITY);
    }
    
    /**
     * 1. Gets a stream of countries and remove the ones that has density = null
     * 2. Gets density one after another
     * 3. Gets the max density using reduce()
     * 4. Assert
     */
    @Test
    public void testHighestPopulationDensity() throws FileNotFoundException {
        
        OptionalDouble highestPopulationDensity = getJsonStream(retrieveCountryByPopulationDensityServiceDoc(), COUNTRIES)
                .filter(country -> !isDensityNull(country))
                .map(country -> getJsonDouble(country, DENSITY))
                .mapToDouble(Double::doubleValue)
                .reduce((Double::max));  //can also be written as: (a, b) -> (a >= b) ? a : b
        
        assertEquals(highestPopulationDensity.getAsDouble(), HIGHEST_POPULATION_DENSITY);
    }
    
    /**
     * 1. Gets a stream of countries and remove the ones that has density = null
     * 2. Gets density one after another
     * 3. Gets the avg density using average()()
     * 4. Assert
     */
    @Test
    public void testAveragePopulationDensityForAllCountries() throws FileNotFoundException {
        
        DecimalFormat dateFormatter = new DecimalFormat("#.###");
        
        double avgPopulationDensity = Double
                .parseDouble(dateFormatter.format(getJsonStream(retrieveCountryByPopulationDensityServiceDoc(), COUNTRIES)
                        .filter(country -> !isDensityNull(country))
                        .map(country -> getJsonDouble(country, DENSITY))
                        .mapToDouble(Double::doubleValue)
                        .average()
                        .getAsDouble()));
        
        assertEquals(avgPopulationDensity, AVG_POPULATION_DENSITY);
    }
    
    /**
     * Test Lowest and Highest population Density from country_by_population_density.json
     */
    @Test
    public void testLowestAndHighestPopulationDensity() throws FileNotFoundException {
        
        // Approach 1:
        testLowestAndHighestPopulationDensityProcedure1();
        
        // Approach 2:
        testLowestAndHighestPopulationDensityProcedure2();
    }
    
    /**
     * Approach 1 using List, forEach() and Map
     * 1. Gets a stream of countries and remove the ones that has density = null
     * 2. Collect a list of country Json object in a list
     * 3. Iterate the countriesList and gets countryName and density
     * 4. Store countryName as a Key and density as a value in a Map
     * 5. Gets the shortestHeight and  tallestHeight from Collections Api
     * 6. Assert
     */
    public static void testLowestAndHighestPopulationDensityProcedure1() throws FileNotFoundException {
        
        Stream<JsonElement> countries = getJsonStream(retrieveCountryByPopulationDensityServiceDoc(), COUNTRIES);
        
        List<JsonElement> countriesList = countries
                .filter(country -> !isDensityNull(country))
                .collect(Collectors.toList());
        
        Map<String, Double> countryNameAndDensityMap = new HashMap<>();
        
        countriesList
                .stream()
                .forEach(country -> countryNameAndDensityMap.put(
                        getJsonString(country, COUNTRY),
                        getJsonDouble(country, DENSITY)));
        
        Double lowestPopulationDensity = Collections.min(countryNameAndDensityMap.values());
        Double highestPopulationDensity = Collections.max(countryNameAndDensityMap.values());
        
        assertEquals(lowestPopulationDensity, LOWEST_POPULATION_DENSITY);
        assertEquals(highestPopulationDensity, HIGHEST_POPULATION_DENSITY);
    }
    
    /**
     * Approach 2 using Collectors.toMap()
     * 1. Gets a stream of countries and remove the ones that has density = null
     * 2. Store countryName as a Key and density as a value in a toMap()
     * 3. Gets the shortestHeight and  tallestHeight from Collections Api
     * 4. Assert
     */
    public static void testLowestAndHighestPopulationDensityProcedure2() throws FileNotFoundException {
        
        Map<String, Double> countryNameAndDensityMap =
                getJsonStream(retrieveCountryByPopulationDensityServiceDoc(), COUNTRIES)
                        .filter(country -> !isDensityNull(country))
                        .collect(Collectors.toMap(
                                country -> getJsonString(country, COUNTRY),
                                density -> getJsonDouble(density, DENSITY)));
        
        assertEquals(Collections.min(countryNameAndDensityMap.values()), LOWEST_POPULATION_DENSITY);
        assertEquals(Collections.max(countryNameAndDensityMap.values()), HIGHEST_POPULATION_DENSITY);
    }
    
    /**
     * Approach 1:
     * 1. Gets the count from country_by_continent.json
     * 2. Gets the count from country_population_density.json
     * 3. Asserts
     */
    @Test
    public void testTotalCountForCountryByContinentAndCountryByDensityProcedure1() throws FileNotFoundException {
        
        long countriesByContinentCount = getJsonStream(retrieveCountryByContinentServiceDoc(), COUNTRIES).count();
        long countriesByPopulationDensityCount  = getJsonStream(retrieveCountryByPopulationDensityServiceDoc(), COUNTRIES).count();
        
        Assert.assertEquals(countriesByContinentCount, countriesByPopulationDensityCount);
    }
    
    /**
     * Approach 2:
     * 1. Gets the count from country_by_continent.json
     * 2. Gets the count from country_population_density.json
     * 3. Asserts
     */
    @Test
    public void func() throws FileNotFoundException {
        
        long countriesByContinentWithCount = getJsonStream(retrieveCountryByContinentServiceDoc(), COUNTRIES).count();
        long countriesByPopulationDensityCount  = getJsonStream(retrieveCountryByPopulationDensityServiceDoc(), COUNTRIES).count();
        
        if (countriesByContinentWithCount != countriesByPopulationDensityCount) {
            Assertions.fail();
        }
    }
    
    
}
