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

import static com.micro_service.workflows.JsonPayloadWorkflow.retrieveCountryByAvgMaleHeightServiceDoc;
import static com.micro_service.workflows.JsonWorkflow.getJsonDouble;
import static com.micro_service.workflows.JsonWorkflow.getJsonStream;
import static com.micro_service.workflows.JsonWorkflow.getJsonString;
import static com.micro_service.workflows.Util.isHeightNull;

public class TestHeightPerCountry extends SuperClass {
    
    @Test
    public void testShortestHeightPerCountry() throws FileNotFoundException {
        
        double shortestHeight = getJsonStream(retrieveCountryByAvgMaleHeightServiceDoc(), "payload.countries")
                .filter(country -> !isHeightNull(country))
                .map(country -> getJsonDouble(country, "height"))
                .mapToDouble(Double::doubleValue)
                .min()
                .getAsDouble();
        
        Assertions.assertEquals(shortestHeight, 160.3);
    }
    
    @Test
    public void testTallestHeightPerCountry() throws FileNotFoundException {
        
        OptionalDouble tallestHeight = getJsonStream(retrieveCountryByAvgMaleHeightServiceDoc(), "payload.countries")
                .filter(country -> !isHeightNull(country))
                .map(country -> getJsonDouble(country, "height"))
                .mapToDouble(Double::doubleValue)
                .reduce((Double::max));  //can also we written as: (a, b) -> (a >= b) ? a : b
        
        Assertions.assertEquals(tallestHeight.getAsDouble(), 181.0);
    }
    
    @Test
    public void findAndTestAvgHeightPerCountry() throws FileNotFoundException {
        
        DecimalFormat df = new DecimalFormat("#.###");
        
        double avgHeight = Double
                .parseDouble(df.format(getJsonStream(retrieveCountryByAvgMaleHeightServiceDoc(), "payload.countries")
                        .filter(country -> !isHeightNull(country))
                        .map(country -> getJsonDouble(country, "height"))
                        .mapToDouble(Double::doubleValue)
                        .average()
                        .getAsDouble()));
        
        Assertions.assertEquals(avgHeight, 171.712);
    }
    
    @Test
    public void testLowestAndHeightHeightProcedure1() throws FileNotFoundException {
        
        Stream<JsonElement> countries = getJsonStream(retrieveCountryByAvgMaleHeightServiceDoc(), "payload.countries");
        
        List<JsonElement> countriesList = countries
                .filter(country -> !isHeightNull(country))
                .collect(Collectors.toList());
        
        Map<String, Double> countryNameAndHeightMap = new HashMap<>();
        
        countriesList
                .stream()
                .forEach(country -> {
                    
                    String countryName = getJsonString(country, "country");
                    double height = getJsonDouble(country, "height");
                    
                    countryNameAndHeightMap.put(countryName, height);
                });
        
        
        Double min = Collections.min(countryNameAndHeightMap.values());
        Double max = Collections.max(countryNameAndHeightMap.values());
        
        Assertions.assertEquals(min, 160.3);
        Assertions.assertEquals(max, 181.0);
    }
    
    @Test
    public void testLowestAndHeightHeightProcedure2() throws FileNotFoundException {
        
        Stream<JsonElement> countries = getJsonStream(retrieveCountryByAvgMaleHeightServiceDoc(), "payload.countries");
        
        // using collectors
//        countries
//                .filter(country -> !isHeightNull(country))
//
//
        System.out.println(countries);
        
    }
}
