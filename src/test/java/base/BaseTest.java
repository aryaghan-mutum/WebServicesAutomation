package base;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {
    
    @BeforeAll
    public static void beforeAllTests() {
    }
    
    @BeforeEach
    public void beforeEachTest() {
    }
    
    /**
     * Convenience shortcut method for logging.
     */
    public static void log(String format, Object... args) {
        System.out.printf(format, args);
        System.out.println("");
    }
    
}
