package com.micro_service.datastructures.sort;

import base.SuperClass;
import com.micro_service.workflows.Util;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

public class MergeSort extends SuperClass {
    
    @Test public void
    
    mergeSort() throws FileNotFoundException {
    
        int[] countryDensityArray = Util.getCountryDensityArray();
        
        int mid = countryDensityArray.length/2;
        
        
        if (countryDensityArray.length == 1) {
            return ;
        }
        
        
        int left = countryDensityArray[mid];
        System.out.println("");
        
    }
    

}
