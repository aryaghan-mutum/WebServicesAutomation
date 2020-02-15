package com.microservice.datastructures.sort;

import base.BaseTest;
import com.microservice.workflows.Util;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.microservice.datastructures.sort.SortHelper.isListSorted;

public class BubbleSort extends BaseTest {
    
    /**
     * Asserts functionalBubbleSort() and sequentialBubbleSort()
     */
    @Test
    public void
    testBubbleSort() throws FileNotFoundException {
        
        int[] countryDensitySequentialBubbleSortArray = sequentialBubbleSort();
        int[] countryDensityFunctionalBubbleSortArray = functionalBubbleSort();
        
        Assert.assertArrayEquals(countryDensitySequentialBubbleSortArray, countryDensityFunctionalBubbleSortArray);
    }
    
    /**
     * Bubble Sort in Sequential Approach:
     * <p>
     * 1. Outer loop: i = 0 to totalSize
     * 2. Inner loop: j = 1 to totalSize -1
     */
    public int[]
    sequentialBubbleSort() throws FileNotFoundException {
        
        int[] countryDensityArray = Util.getCountryDensityArray();
        
        // check if the list is sorted
        isListSorted(
                Arrays.stream(countryDensityArray)
                        .boxed()
                        .collect(Collectors.toList()));
        
        int totalSize = countryDensityArray.length;
        
        for (int i = 0; i < totalSize; i++) {
            
            for (int j = 1; j < totalSize - i; j++) {
                
                if (countryDensityArray[j - 1] > countryDensityArray[j]) {
                    //swap
                    int temp = countryDensityArray[j - 1];
                    countryDensityArray[j - 1] = countryDensityArray[j];
                    countryDensityArray[j] = temp;
                }
            }
        }
        
        return countryDensityArray;
    }
    
    /**
     * Bubble Sort in Functional Approach:
     */
    public int[]
    functionalBubbleSort() throws FileNotFoundException {
        
        int[] countryDensityArray = Util.getCountryDensityArray();
    
        // check if the list is sorted
        isListSorted(
                Arrays.stream(countryDensityArray)
                        .boxed()
                        .collect(Collectors.toList()));
        
        int totalSize = countryDensityArray.length;
        
        IntStream.range(0, totalSize - 1)
                .flatMap(i -> IntStream.range(1, totalSize - i))
                .forEach(j -> {
                    
                    if (countryDensityArray[j - 1] > countryDensityArray[j]) {
                        
                        // swap
                        int temp = countryDensityArray[j];
                        countryDensityArray[j] = countryDensityArray[j - 1];
                        countryDensityArray[j - 1] = temp;
                    }
                    
                });
        
        return countryDensityArray;
    }
    
}
