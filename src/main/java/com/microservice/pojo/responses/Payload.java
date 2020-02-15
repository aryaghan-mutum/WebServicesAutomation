
package com.microservice.pojo.responses;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "about",
    "movies"
})

@Data
public class Payload {

    @JsonProperty("about")
    private About about;
    @JsonProperty("movies")
    private List<Movie> movies = null;

}
