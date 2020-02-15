
package com.microservice.pojo.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "budget",
    "boxOffice",
    "boxOfficeInUS"
})

@Data
public class Cost {

    @JsonProperty("budget")
    private String budget;
    @JsonProperty("boxOffice")
    private String boxOffice;
    @JsonProperty("boxOfficeInUS")
    private Object boxOfficeInUS;

}
