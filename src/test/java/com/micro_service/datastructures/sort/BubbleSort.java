package com.micro_service.datastructures.sort;

import base.SuperClass;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.stream.IntStream;

import static com.micro_service.workflows.ConstantsWorkflow.COUNTRIES;
import static com.micro_service.workflows.ConstantsWorkflow.DENSITY;
import static com.micro_service.workflows.JsonPayloadWorkflow.retrieveCountryByPopulationDensityServiceDoc;
import static com.micro_service.workflows.JsonWorkflow.getJsonStream;
import static com.micro_service.workflows.JsonWorkflow.getJsonString;
import static com.micro_service.workflows.Util.isDensityNull;

public class BubbleSort extends SuperClass {
    
    /**
     * Asserts functionalBubbleSort() and imperativeBubbleSort()
     */
    @Test
    public void
    testBubbleSort() throws FileNotFoundException {
        
        int[] countryDensityFunctionalBubbleSortArray = functionalBubbleSort();
        int[] countryDensityImperativeBubbleSortArray = imperativeBubbleSort();
        
        Assert.assertArrayEquals(countryDensityFunctionalBubbleSortArray, countryDensityImperativeBubbleSortArray);
    }
    
    /**
     * Bubble Sort in Functional Approach:
     */
    public int[]
    functionalBubbleSort() throws FileNotFoundException {
        
        int[] countryDensityArray = getCountryDensityArray();
        
        int length = countryDensityArray.length;
        IntStream.range(0, length - 1)
                .flatMap(i -> IntStream.range(1, length - i))
                .forEach(j -> {
                    
                    if (countryDensityArray[j - 1] > countryDensityArray[j]) {
                        Integer temp = countryDensityArray[j];
                        countryDensityArray[j] = countryDensityArray[j - 1];
                        countryDensityArray[j - 1] = temp;
                    }
                    
                });
        
        return countryDensityArray;
    }
    
    /**
     * Bubble Sort in Imperative Approach:
     */
    public int[]
    imperativeBubbleSort() throws FileNotFoundException {
        
        int[] countryDensityArray = getCountryDensityArray();
        
        for (int i = 0; i < countryDensityArray.length - 1; i++) {
            for (int j = 0; j < countryDensityArray.length - i - 1; j++) {
                if (countryDensityArray[j] > countryDensityArray[j + 1]) {
                    Integer temp = countryDensityArray[j];
                    countryDensityArray[j] = countryDensityArray[j + 1];
                    countryDensityArray[j + 1] = temp;
                }
            }
        }
        
        return countryDensityArray;
    }
    
    /**
     * Operations used: filter(), map(), mapToInt()
     * Gets aan array of densities from a json file
     */
    private int[]
    getCountryDensityArray() throws FileNotFoundException {
        return getJsonStream(retrieveCountryByPopulationDensityServiceDoc(), COUNTRIES)
                .filter(country -> !isDensityNull(country))
                .map(country -> getJsonString(country, DENSITY))
                .map(country -> Double.valueOf(country))
                .mapToInt(Double::intValue)
                .toArray();
        
    }
    
}
