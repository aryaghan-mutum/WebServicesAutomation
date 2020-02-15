package com.microservice.datastructures.sort;

import base.BaseTest;
import com.microservice.workflows.Util;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

public class SelectionSort extends BaseTest {
    
    /**
     * Asserts functionalBubbleSort() and imperativeBubbleSort()
     */
    @Test
    public void
    testBubbleSort() throws FileNotFoundException {
        
        int[] countryDensitySequentialSelectionSortArray = sequentialSelectionSort();
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
