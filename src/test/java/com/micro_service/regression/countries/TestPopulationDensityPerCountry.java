package com.micro_service.regression.countries;

import base.SuperClass;
import com.google.gson.JsonElement;
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
import static com.micro_service.workflows.JsonPayloadWorkflow.retrieveCountryByPopulationDensityServiceDoc;
import static com.micro_service.workflows.JsonWorkflow.getJsonDouble;
import static com.micro_service.workflows.JsonWorkflow.getJsonStream;
import static com.micro_service.workflows.JsonWorkflow.getJsonString;
import static com.micro_service.workflows.Util.isDensityNull;

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
        
        Assertions.assertEquals(lowestPopulationDensity, LOWEST_POPULATION_DENSITY);
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
        
        Assertions.assertEquals(highestPopulationDensity.getAsDouble(), HIGHEST_POPULATION_DENSITY);
    }
    
    /**
     * 1. Gets a stream of countries and remove the ones that has density = null
     * 2. Gets density one after another
     * 3. Gets the avg density using average()()
     * 4. Assert
     */
    @Test
    public void testAveragePopulationDensityAllCountries() throws FileNotFoundException {
        
        DecimalFormat dateFormatter = new DecimalFormat("#.###");
        
        double avgPopulationDensity = Double
                .parseDouble(dateFormatter.format(getJsonStream(retrieveCountryByPopulationDensityServiceDoc(), COUNTRIES)
                        .filter(country -> !isDensityNull(country))
                        .map(country -> getJsonDouble(country, DENSITY))
                        .mapToDouble(Double::doubleValue)
                        .average()
                        .getAsDouble()));
        
        Assertions.assertEquals(avgPopulationDensity, AVG_POPULATION_DENSITY);
    }
    
    /**
     * 1. Gets a stream of countries and remove the ones that has density = null
     * 2. Collect a list of country Json object in a list
     * 3. Iterate the countriesList and gets countryName and density
     * 4. Store countryName as a Key and density as a value in a Map
     * 5. Gets the shortestHeight and  tallestHeight from Collections Api
     * 6. Assert
     */
    @Test
    public void testLowestAndHighestPopulationDensityProcedure1() throws FileNotFoundException {
        
        Stream<JsonElement> countries = getJsonStream(retrieveCountryByPopulationDensityServiceDoc(), COUNTRIES);
        
        List<JsonElement> countriesList = countries
                .filter(country -> !isDensityNull(country))
                .collect(Collectors.toList());
        
        Map<String, Double> countryNameAndDensityMap = new HashMap<>();
        
        countriesList
                .stream()
                .forEach(country -> countryNameAndDensityMap.put(getJsonString(country, COUNTRY), getJsonDouble(country, DENSITY)));
        
        Double lowestPopulationDensity = Collections.min(countryNameAndDensityMap.values());
        Double highestPopulationDensity = Collections.max(countryNameAndDensityMap.values());
        
        Assertions.assertEquals(lowestPopulationDensity, LOWEST_POPULATION_DENSITY);
        Assertions.assertEquals(highestPopulationDensity, HIGHEST_POPULATION_DENSITY);
    }
    
    @Test
    public void testLowestAndHighestPopulationDensityProcedure2() throws FileNotFoundException {
        
        Stream<JsonElement> countries = getJsonStream(retrieveCountryByPopulationDensityServiceDoc(), COUNTRIES);
        
        // using collectors
//        countries
//                .filter(country -> !isHeightNull(country))
//
//
        System.out.println(countries);
        
    }
}
