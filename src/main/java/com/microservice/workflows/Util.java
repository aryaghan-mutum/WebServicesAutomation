package com.microservice.workflows;

import com.google.gson.JsonElement;

import java.io.FileNotFoundException;
import java.time.LocalDate;

import static com.microservice.workflows.ConstantsWorkflow.ACTOR3;
import static com.microservice.workflows.ConstantsWorkflow.CONTINENT;
import static com.microservice.workflows.ConstantsWorkflow.COUNTRIES;
import static com.microservice.workflows.ConstantsWorkflow.DENSITY;
import static com.microservice.workflows.JsonPayloadWorkflow.retrieveCountryByPopulationDensityServiceDoc;
import static com.microservice.workflows.JsonWorkflow.getJsonStream;
import static com.microservice.workflows.JsonWorkflow.getJsonString;
import static com.microservice.workflows.JsonWorkflow.isFieldUndefined;

public class Util {
    
    /**
     * Returns the current year as a type: integer
     */
    public static int getCurrentYear() {
        int year = LocalDate.now().getYear();
        return year;
    }
    
    /**
     * 1. Parses the String dateReleased variable and extracts year, month and dayOfMont
     * 2. And converts to LocalDate object
     */
    public static LocalDate convertStringToLocalDateFormat(String dateReleased) {
        return LocalDate.of(Integer.parseInt(dateReleased.substring(4, 8)),
                Integer.parseInt(dateReleased.substring(0, 2)),
                Integer.parseInt(dateReleased.substring(4, 6)));
    }
    
    public static boolean isDensityNull(JsonElement country) {
        return isFieldUndefined(country, DENSITY) || getJsonString(country, DENSITY) == null;
    }
    
    public static boolean isContinentNull(JsonElement country) {
        return isFieldUndefined(country, CONTINENT) || getJsonString(country, CONTINENT) == null;
    }
    
    public static boolean isActor3Null(JsonElement country) {
        return isFieldUndefined(country, ACTOR3) || getJsonString(country, ACTOR3) == null;
    }
    
    /**
     * Operations used: filter(), map(), mapToInt()
     * Gets an array of population densities from a json file
     */
    public static int[] getCountryDensityArray() throws FileNotFoundException {
        return getJsonStream(retrieveCountryByPopulationDensityServiceDoc(), COUNTRIES)
                .filter(country -> !isDensityNull(country))
                .map(country -> getJsonString(country, DENSITY))
                .map(country -> Double.valueOf(country))
                .mapToInt(Double::intValue)
                .toArray();
    }
    
}
