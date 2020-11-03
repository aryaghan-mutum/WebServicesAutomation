package com.microservice.regression.gson.movies;

import com.google.gson.JsonElement;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.microservice.workflows.ConstantsWorkflow.DIRECTOR;
import static com.microservice.workflows.ConstantsWorkflow.MOVIES;
import static com.microservice.workflows.ConstantsWorkflow.PRODUCER;
import static com.microservice.workflows.ConstantsWorkflow.TITLE;
import static com.microservice.workflows.JsonPayloadWorkflow.retrieveMoviesServiceDoc;
import static com.microservice.workflows.JsonWorkflow.getJsonStream;
import static com.microservice.workflows.JsonWorkflow.getJsonString;
import static com.microservice.workflows.Util.log;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

/**
 * @author Anurag Muthyam
 * url: https://github.com/aryaghan-mutum
 */

public class TestDirectorAndProducerAreSame {
    
    /**
     * The Test case validates if a director for a movie is also a producer.
     * 1. Gets a list of directors for each movie from movies_service.json
     * 2. Gets a list of producers for each movie from movies_service.json
     * 3. If yhe director for a movie is also a producer then collects them in a Set.
     */
    @Test
    @DisplayName("Test Director And Producer Are Same For A Movie")
    public void testDirectorAndProducerAreSameForAMovie() throws FileNotFoundException {
        Assert.assertEquals(checkIfDirectorAndProducerAreSameForAMovieProcedure1(), checkIfDirectorAndProducerAreSameForAMovieProcedure2());
    }
    
    @Step("Approach 1 using foreach():")
    public static Set<Set<String>> checkIfDirectorAndProducerAreSameForAMovieProcedure1() throws FileNotFoundException {
        
        Set<Set<String>> directorIsProducerForMovieSet = new HashSet<>();
        
        getJsonStream(retrieveMoviesServiceDoc(), MOVIES)
                .forEach(movie -> {
                    
                    String movieTitle = getJsonString(movie, TITLE);
                    
                    // get a list of directors for a  movie
                    List<String> directorList = getJsonStream(movie, DIRECTOR)
                            .map(JsonElement::getAsString)
                            .collect(toList());
                    
                    // get a list of producers for a  movie
                    List<String> producerList = getJsonStream(movie, PRODUCER)
                            .map(JsonElement::getAsString)
                            .collect(toList());
                    
                    directorIsProducerForMovieSet
                            .add(directorList
                                    .stream()
                                    .filter(director -> producerList.contains(director))
                                    .peek(director -> log("Director: %s is also a Producer for a movie: %s", director, movieTitle))
                                    .map(mT -> movieTitle)
                                    .collect(toSet()));
                    
                });
        
        return directorIsProducerForMovieSet;
    }
  
    @Step("Approach 2 using forEach(), map(), filter() and collect()")
    public Set<Set<String>> checkIfDirectorAndProducerAreSameForAMovieProcedure2() throws FileNotFoundException {
        
        Set<Set<String>> directorIsProducerForMovieSet = new HashSet<>();
        
        getJsonStream(retrieveMoviesServiceDoc(), MOVIES)
                .forEach(movie -> directorIsProducerForMovieSet
                        .add(getJsonStream(movie, DIRECTOR)
                                .map(JsonElement::getAsString)
                                .filter(director -> getListOfProducersForAMovie(movie).contains(director))
                                .peek(director -> log("Director: %s is also a Producer for a movie: %s", director, getJsonString(movie, TITLE)))
                                .map(mT -> getJsonString(movie, TITLE))
                                .collect(toSet())));
        
        return directorIsProducerForMovieSet;
    }
    
    @Step("Get a list of producers for a movie")
    private List<String> getListOfProducersForAMovie(JsonElement movie) {
        return getJsonStream(movie, PRODUCER)
                .map(JsonElement::getAsString)
                .collect(toList());
    }
}
