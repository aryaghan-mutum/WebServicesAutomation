package com.micro_service.datastructures.sort;

import java.util.List;

public class SortHelper {
    
    public static boolean isListSorted(List<Integer> elementsList) {
    
        for (int i = 0; i < elementsList.size() - 1; i++) {
        
            if (elementsList.get(i) > elementsList.get(i + 1)) {
            
                String errorMsg = String.format("Elements: %s, %s not in order at index=%s ", elementsList.get(i), elementsList.get(i + 1), i);
                System.err.println(errorMsg);
            
                return false;
            }
        
        }
    
        return true;
    }
    
}