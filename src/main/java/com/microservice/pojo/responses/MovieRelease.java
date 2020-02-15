
package com.microservice.pojo.responses;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "countryReleased",
    "movieItem"
})

@Data
public class MovieRelease {

    @JsonProperty("countryReleased")
    private String countryReleased;
    @JsonProperty("movieItem")
    private List<MovieItem> movieItem = null;

}
