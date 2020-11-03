package com.microservice.workflows;

/**
 * @author Anurag Muthyam
 * url: https://github.com/aryaghan-mutum
 */

public class ConstantsWorkflow {

    // JSON PATH (Must be changed based on where the project is located)
    public static final String MOVIE_SERVICE_PATH = "/Users/anuragmuthyam/Documents/dev/java-projects/WebServicesAutomation/src/main/resources/data/json/movies_service.json";
    public static final String COUNTRY_BY_POPULATION_DENSITY = "/Users/anuragmuthyam/Documents/dev/java-projects/WebServicesAutomation/src/main/resources/data/json/country_by_population_density.json";
    public static final String COUNTRY_BY_CONTINENT = "/Users/anuragmuthyam/Documents/dev/java-projects/WebServicesAutomation/src/main/resources/data/json/country_by_continent.json";
    
    // MOVIES
    public static final String MOVIES = "payload.movies";
    public static final String DAYS = "movie.days";
    public static final String TITLE = "title";
    public static final String YEAR_RELEASED = "yearReleased";
    public static final String DATE_RELEASED = "dateReleased";
    public static final String LANGUAGE = "movieLanguage.language";
    public static final String CAST = "cast";
    public static final String DIRECTOR = "director";
    public static final String PRODUCER = "producer";
    public static final String ACTOR1 = "actor1";
    public static final String ACTOR2 = "actor2";
    public static final String ACTOR3 = "actor3";
    public static final String BUDGET = "cost.budget";
    public static final String BOX_OFFICE = "cost.boxOffice";
    public static final String MOVIE_RELEASE = "movie.movieRelease";
    public static final String COUNTRY_RELEASED = "countryReleased";
    public static final String MOVIE_ITEM = "movieItem";
    public static final String MOVIE_RELEASED_STATE = "movieReleasedState";
    public static final String MOVIE_RELEASED_PRICE = "moveReleasedPrice";
    
    // COUNTRY BY AVG MALE HEIGHT
    public static final String COUNTRIES = "payload.countries";
    public static final String COUNTRY = "country";
    public static final String DENSITY = "density";
    public static final String CONTINENT = "continent";

}
