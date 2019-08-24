package com.micro_service.workflows;

import java.time.LocalDate;

public class Util {
    
    /**
     * Returns the current year as a type: integer
     */
    public static int getCurrentYear() {
        int year = LocalDate.now().getYear();
        return year;
    }
    
    /**
     * 1. Parses the String dateReleased variable and extracts year, month and dayOfMont
     * 2. And converts to LocalDate object
     */
    public static LocalDate convertStringToLocalDateFormat(String dateReleased) {
        return LocalDate.of(Integer.parseInt(dateReleased.substring(4, 8)),
                Integer.parseInt(dateReleased.substring(0, 2)),
                Integer.parseInt(dateReleased.substring(4, 6)));
    }
    
}
