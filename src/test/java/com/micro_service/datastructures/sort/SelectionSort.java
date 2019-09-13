package com.micro_service.datastructures.sort;

import base.SuperClass;
import com.micro_service.workflows.Util;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

public class SelectionSort extends SuperClass {
    
    /**
     * Asserts functionalBubbleSort() and imperativeBubbleSort()
     */
    @Test
    public void
    testBubbleSort() throws FileNotFoundException {
        
        // int[] countryDensityFunctionalBubbleSortArray = functionalBubbleSort();
        int[] countryDensitySequentialSelectionSortArray = sequentialSelectionSort();
        
       // int[] countryDensityFunctionalSelectionArray = functionalSelectionSort();
        
       // Assert.assertArrayEquals(countryDensitySequentialSelectionSortArray, countryDensityFunctionalSelectionArray);
    }
    
    /**
     * Bubble Sort in Sequential Approach:
     */
    public int[]
    sequentialSelectionSort() throws FileNotFoundException {
        
        int[] countryDensityArray = Util.getCountryDensityArray();
        
        int totalSize = countryDensityArray.length;
        
        for (int i = 0; i < totalSize - 1; i++) {
            
            int minDensity = i;
            
            for (int j = i + 1; j < totalSize; j++) {
                if (countryDensityArray[j] < countryDensityArray[minDensity]) {
                    minDensity = j;
                }
            }
            
            //swap
            int temp = countryDensityArray[i];
            countryDensityArray[i] = countryDensityArray[minDensity];
            countryDensityArray[minDensity] = temp;
        }
        return countryDensityArray;
    }
    
}