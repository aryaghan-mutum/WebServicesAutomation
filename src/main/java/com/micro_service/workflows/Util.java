package com.micro_service.workflows;

import com.google.gson.JsonElement;

import java.io.FileNotFoundException;
import java.time.LocalDate;

import static com.micro_service.workflows.ConstantsWorkflow.CONTINENT;
import static com.micro_service.workflows.ConstantsWorkflow.COUNTRIES;
import static com.micro_service.workflows.ConstantsWorkflow.DENSITY;
import static com.micro_service.workflows.JsonPayloadWorkflow.retrieveCountryByPopulationDensityServiceDoc;
import static com.micro_service.workflows.JsonWorkflow.getJsonStream;
import static com.micro_service.workflows.JsonWorkflow.getJsonString;
import static com.micro_service.workflows.JsonWorkflow.isUndefined;

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
        return isUndefined(country, DENSITY) || getJsonString(country, DENSITY) == null;
    }
    
    public static boolean isContinentNull(JsonElement country) {
        return isUndefined(country, CONTINENT) || getJsonString(country, CONTINENT) == null;
    }
    
    /**
     * Operations used: filter(), map(), mapToInt()
     * Gets aan array of densities from a json file
     */
    public static int[]
    getCountryDensityArray() throws FileNotFoundException {
        return getJsonStream(retrieveCountryByPopulationDensityServiceDoc(), COUNTRIES)
                .filter(country -> !isDensityNull(country))
                .map(country -> getJsonString(country, DENSITY))
                .map(country -> Double.valueOf(country))
                .mapToInt(Double::intValue)
                .toArray();
    }
    
}
