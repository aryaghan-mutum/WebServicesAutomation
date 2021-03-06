
package com.microservice.pojo.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "payloadDescription",
    "totalMovies"
})

@Data
public class About {

    @JsonProperty("payloadDescription")
    private String payloadDescription;
    @JsonProperty("totalMovies")
    private String totalMovies;

}
