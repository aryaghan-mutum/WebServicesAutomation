package com.micro_service.workflows;

public class ConstantsWorkflow {
    
    public static final String MOVIE_SERVICE_PATH = "/Users/anuragmuthyam/Documents/dev/WebServicesAutomation/src/main/resources/data/movies_service.json";
    public static final String COUNTRY_BY_POPULATION_DENSITY = "/Users/anuragmuthyam/Documents/dev/WebServicesAutomation/src/main/resources/data/country_by_population_density.json";
    
    // MOVIES
    public static final String MOVIES = "payload.movies";
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
    
    // COUNTRY BY AVG MALE HEIGHT
    public static final String COUNTRIES = "payload.countries";
    public static final String COUNTRY = "country";
    public static final String DENSITY = "density";
    
}
