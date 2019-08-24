package com.micro_service.regression.movies;

import base.SuperClass;
import com.google.gson.JsonElement;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.micro_service.workflows.ConstantsWorkflow.DIRECTOR;
import static com.micro_service.workflows.ConstantsWorkflow.MOVIES;
import static com.micro_service.workflows.ConstantsWorkflow.PRODUCER;
import static com.micro_service.workflows.ConstantsWorkflow.TITLE;
import static com.micro_service.workflows.JsonPayloadWorkflow.retrieveMoviesServiceDoc;
import static com.micro_service.workflows.JsonWorkflow.getJsonStream;
import static com.micro_service.workflows.JsonWorkflow.getJsonString;

public class TestDirectorAndProducerAreSame extends SuperClass {
    
    /**
     * The Test case validates if a director for a movie is also a producer.
     * 1. Gets a list of directors for each movie from movies_service.json
     * 2. Gets a list of producers for each movie from movies_service.json
     * 3. If yhe director for a movie is also a producer then collects them in a Set.
     */
    @Test
    public void testDirectorAndProducerAreSameForAMovie() throws FileNotFoundException {
        Assert.assertEquals(checkIfDirectorAndProducerAreSameForAMovieProcedure1(), checkIfDirectorAndProducerAreSameForAMovieProcedure2());
    }
    
    /**
     * Approach 1 using foreach():
     */
    public static Set<Set<String>> checkIfDirectorAndProducerAreSameForAMovieProcedure1() throws FileNotFoundException {
        
        Set<Set<String>> directorIsProducerForMovieSet = new HashSet<>();
        
        getJsonStream(retrieveMoviesServiceDoc(), MOVIES)
                .forEach(movie -> {
                    
                    String movieTitle = getJsonString(movie, TITLE);
                    
                    // get a list of directors for a  movie
                    List<String> directorList = getJsonStream(movie, DIRECTOR)
                            .map(producer -> producer.getAsString())
                            .collect(Collectors.toList());
                    
                    // get a list of producers for a  movie
                    List<String> producerList = getJsonStream(movie, PRODUCER)
                            .map(producer -> producer.getAsString())
                            .collect(Collectors.toList());
                    
                    directorIsProducerForMovieSet
                            .add(directorList
                                    .stream()
                                    .filter(director -> producerList.contains(director))
                                    .peek(director -> log("Director: %s is also a Producer for a movie: %s", director, movieTitle))
                                    .map(mT -> movieTitle)
                                    .collect(Collectors.toSet()));
                    
                });
        
        return directorIsProducerForMovieSet;
    }
    
    /**
     * Approach 2 using forEach(), map(), filter() and collect():
     */
    public Set<Set<String>> checkIfDirectorAndProducerAreSameForAMovieProcedure2() throws FileNotFoundException {
        
        Set<Set<String>> directorIsProducerForMovieSet = new HashSet<>();
        
        getJsonStream(retrieveMoviesServiceDoc(), MOVIES)
                .forEach(movie -> directorIsProducerForMovieSet
                        .add(getJsonStream(movie, DIRECTOR)
                                .map(producer -> producer.getAsString())
                                .filter(director -> getListOfProducersForAMovie(movie).contains(director))
                                .peek(director -> log("Director: %s is also a Producer for a movie: %s", director, getJsonString(movie, TITLE)))
                                .map(mT -> getJsonString(movie, TITLE))
                                .collect(Collectors.toSet())));
        
        return directorIsProducerForMovieSet;
    }
    
    private List<String> getListOfProducersForAMovie(JsonElement movie) {
        return getJsonStream(movie, PRODUCER)
                .map(producer -> producer.getAsString())
                .collect(Collectors.toList());
    }
}
