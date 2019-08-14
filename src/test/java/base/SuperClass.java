package base;

import com.micro_service.workflows.Settings;
import com.micro_service.workflows.SettingsWorkflow;
import com.micro_service.workflows.Util;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

public class SuperClass {
    
    protected static Settings contour;
    
    @BeforeAll
    public static void beforeAllTests() {
        contour = SettingsWorkflow.loadConfig();
      
        try {
            Util.initResponsesFolder();
        } catch (IOException e) {
            log("ERROR: Failed to initialize responses folder.");
        }
    }
    
    @BeforeEach
    public void beforeEachTest() {
    }
    
    /**
     * Convenience shortcut method for logging.
     *
     * @param format
     * @param args
     */
    public static void log(String format, Object... args) {
        System.out.printf(format, args);
        System.out.println("");
    }
    
}
