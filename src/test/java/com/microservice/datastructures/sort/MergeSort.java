package com.microservice.datastructures.sort;

import base.SuperClass;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static com.microservice.workflows.Util.getCountryDensityArray;

public class MergeSort extends SuperClass {
    
    // Work In Progress
    @Test public void
    mergeSort() throws FileNotFoundException {
    
        int[] countryDensityArray = getCountryDensityArray();
        
        int mid = countryDensityArray.length/2;
        
        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();
        
        sort(leftList, rightList, countryDensityArray);
        
        
        
        //merge
        
    }
    
    private static void sort(List<Integer> leftList, List<Integer> rightList, int[] countryDensityArray){

    }
    
    
    

}
