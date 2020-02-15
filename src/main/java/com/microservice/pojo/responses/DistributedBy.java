
package com.microservice.pojo.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "distributor1",
    "distributor2"
})

@Data
public class DistributedBy {

    @JsonProperty("distributor1")
    private String distributor1;
    @JsonProperty("distributor2")
    private String distributor2;

}
