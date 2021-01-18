package com.microservice.workflows;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;

import static com.microservice.workflows.ConstantsWorkflow.ACTOR3;
import static com.microservice.workflows.ConstantsWorkflow.CONTINENT;
import static com.microservice.workflows.ConstantsWorkflow.COUNTRIES;
import static com.microservice.workflows.ConstantsWorkflow.DENSITY;
import static com.microservice.workflows.JsonPayloadWorkflow.retrieveCountryByPopulationDensityServiceDoc;
import static com.microservice.workflows.JsonWorkflow.getJsonStream;
import static com.microservice.workflows.JsonWorkflow.getJsonString;
import static com.microservice.workflows.JsonWorkflow.isFieldUndefined;

/**
 * @author Anurag Muthyam
 * url: https://github.com/aryaghan-mutum
 */

public class Util {

    /**
     * Convenience shortcut method for logging.
     */
    private static void log(String format, Object... args) {
        System.out.printf(format, args);
        System.out.println("");
    }

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

    /**
     * Check if the density is null
     */
    public static boolean isDensityNull(JsonElement country) {
        return isFieldUndefined(country, DENSITY) || getJsonString(country, DENSITY) == null;
    }

    /**
     * Check if the continent is null
     */
    public static boolean isContinentNull(JsonElement country) {
        return isFieldUndefined(country, CONTINENT) || getJsonString(country, CONTINENT) == null;
    }

    /**
     * Operations used: filter(), map(), mapToInt()
     * Gets an array of population densities from a json file
     */
    public static int[] getCountryDensityArray() throws FileNotFoundException {
        return getJsonStream(retrieveCountryByPopulationDensityServiceDoc(), COUNTRIES)
                .filter(country -> !isDensityNull(country))
                .map(country -> getJsonString(country, DENSITY))
                .map(Double::valueOf)
                .mapToInt(Double::intValue)
                .toArray();
    }

    /**
     * Reads Json file from the project structure.
     */
    public static JsonElement parseJsonFileFromProjectStructure() throws FileNotFoundException {
        return new JsonParser().parse(new FileReader(
                "/Users/yourUserName/Documents/dev/yourProjectName/search_shorex.json"));
    }

}
