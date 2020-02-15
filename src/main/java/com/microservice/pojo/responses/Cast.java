
package com.microservice.pojo.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "actor1",
    "actor2",
    "actor3"
})

@Data
public class Cast {

    @JsonProperty("actor1")
    private String actor1;
    @JsonProperty("actor2")
    private String actor2;
    @JsonProperty("actor3")
    private String actor3;

}
