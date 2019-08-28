package com.micro_service.datastructures.sort;

import base.SuperClass;
import com.micro_service.workflows.Util;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.stream.IntStream;

public class BubbleSort extends SuperClass {
    
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
     */
    public int[]
    sequentialBubbleSort() throws FileNotFoundException {
        
        int[] countryDensityArray = Util.getCountryDensityArray();
        
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
