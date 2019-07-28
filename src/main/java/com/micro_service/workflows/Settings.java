package com.micro_service.workflows;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Settings {
    
    String shipCode;
    String sailDate;
    String environment;
    String appKey;
    String solrIndex;
    String folderPath;
    String serviceURL;
    String solrHost;
    String dispatcherURL;
   // JsonObject json;
}
