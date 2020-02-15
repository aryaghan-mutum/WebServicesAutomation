
package com.microservice.pojo.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "movieReleasedState",
    "moveReleasedPrice",
    "movieReleasedPrice"
})

@Data
public class MovieItem {

    @JsonProperty("movieReleasedState")
    private String movieReleasedState;
    @JsonProperty("moveReleasedPrice")
    private Object moveReleasedPrice;
    @JsonProperty("movieReleasedPrice")
    private String movieReleasedPrice;

}
