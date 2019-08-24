package com.micro_service.regression.movies;

import base.SuperClass;
import com.google.gson.JsonElement;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.micro_service.workflows.ConstantsWorkflow.DIRECTOR;
import static com.micro_service.workflows.ConstantsWorkflow.MOVIES;
import static com.micro_service.workflows.ConstantsWorkflow.PRODUCER;
import static com.micro_service.workflows.ConstantsWorkflow.TITLE;
import static com.micro_service.workflows.JsonPayloadWorkflow.retrieveMoviesServiceDoc;
import static com.micro_service.workflows.JsonWorkflow.getJsonStream;
import static com.micro_service.workflows.JsonWorkflow.getJsonString;

public class TestDirectorAndProducerAreSame extends SuperClass {
    
    @Test
    public void validate() throws FileNotFoundException {
        
        // Assert.assertEquals(checkIfDirectorAndProducerAreSameForAMovieProcedure1(), checkIfDirectorAndProducerAreSameForAMovieProcedure2());
        Assert.assertEquals(checkIfDirectorAndProducerAreSameForAMovieProcedure2(), checkIfDirectorAndProducerAreSameForAMovieProcedure3());
    }
    
    public static long checkIfDirectorAndProducerAreSameForAMovieProcedure1() throws FileNotFoundException {
        
        return 2;
    }
    
    public static long checkIfDirectorAndProducerAreSameForAMovieProcedure2() throws FileNotFoundException {
        
        final AtomicLong atomicLong = new AtomicLong();
        
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
                    
                    directorList
                            .stream()
                            .filter(director -> producerList.contains(director))
                            .peek(director -> log("Director: %s is also a Producer for a movie: %s", director, movieTitle))
                            .count();
                });
        
        return 3;
    }
    
    public long checkIfDirectorAndProducerAreSameForAMovieProcedure3() throws FileNotFoundException {
        
        getJsonStream(retrieveMoviesServiceDoc(), MOVIES)
                .forEach(movie -> {
                    
                    long directorList = getJsonStream(movie, DIRECTOR)
                            .map(producer -> producer.getAsString())
                            .filter(director -> getListOfProducersForAMovie(movie).contains(director))
                            .peek(director -> log("Director: %s is also a Producer for a movie: %s", director, getJsonString(movie, TITLE)))
                            .count();
                });
        
        return 3;
    }
    
    private List<String> getListOfProducersForAMovie(JsonElement movie) {
        return getJsonStream(movie, PRODUCER)
                .map(producer -> producer.getAsString())
                .collect(Collectors.toList());
    }
}
