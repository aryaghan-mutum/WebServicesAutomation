package com.micro_service.regression.countries;

import base.SuperClass;
import com.google.gson.JsonElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.micro_service.workflows.JsonPayloadWorkflow.retrieveCountryByAvgMaleHeightServiceDoc;
import static com.micro_service.workflows.JsonWorkflow.getJsonDouble;
import static com.micro_service.workflows.JsonWorkflow.getJsonInt;
import static com.micro_service.workflows.JsonWorkflow.getJsonStream;
import static com.micro_service.workflows.JsonWorkflow.getJsonString;
import static com.micro_service.workflows.JsonWorkflow.isUndefined;
import static com.micro_service.workflows.Util.isHeightNull;

public class CountryByAvgMaleHeight extends SuperClass {
    
    public static void main(String[] args) throws FileNotFoundException {
        
        log("Country Names %s:", getCountriesList());
        
        getCountryNamesWithHeightAsNotNull();
        
        System.out.println("-");
    }
    
    public static List<String> getCountriesList() throws FileNotFoundException {
        return getJsonStream(retrieveCountryByAvgMaleHeightServiceDoc(), "payload.countries")
                .map(country -> getJsonString(country, "country"))
                .collect(Collectors.toList());
    }
    
    public static List<String> getCountryNamesWithHeightAsNotNull() throws FileNotFoundException {
        
        List<String> countryNamesWithHeightNotNullList = new ArrayList<>();
        
        getJsonStream(retrieveCountryByAvgMaleHeightServiceDoc(), "payload.countries")
                .forEach(country -> {
                    
                    String countryName = getJsonString(country, "country");
                    
                    if (!isHeightNull(country)) {
                        countryNamesWithHeightNotNullList.add(countryName);
                    }
                });
        
        return countryNamesWithHeightNotNullList;
    }
    
}
