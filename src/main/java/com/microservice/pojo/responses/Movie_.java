
package com.microservice.pojo.responses;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "days",
    "movieRelease"
})

@Data
public class Movie_ {

    @JsonProperty("days")
    private List<Object> days = null;
    @JsonProperty("movieRelease")
    private List<MovieRelease> movieRelease = null;

}
