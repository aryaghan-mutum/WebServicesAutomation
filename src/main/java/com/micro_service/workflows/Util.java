package com.micro_service.workflows;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Util {
    
    public static void initResponsesFolder() throws IOException {
        final String folder = "responses";
        final Path folderPath = Paths.get(folder);
        
        if (Files.exists(folderPath)) {
            //Delete all files
            Files.walk(folderPath)
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .forEach(File::delete);
        } else {
            Files.createDirectory(folderPath);
        }
    }
    
}
