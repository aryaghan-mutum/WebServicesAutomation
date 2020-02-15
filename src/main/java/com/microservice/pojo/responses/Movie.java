
package com.microservice.pojo.responses;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "title",
    "yearReleased",
    "runningTime",
    "dateReleased",
    "basedOn",
    "movieLanguage",
    "cast",
    "director",
    "producer",
    "musicDirector",
    "cinematography",
    "distributedBy",
    "productionCompany",
    "movie",
    "genres",
    "cost"
})

@Data
public class Movie {

    @JsonProperty("title")
    private String title;
    @JsonProperty("yearReleased")
    private Integer yearReleased;
    @JsonProperty("runningTime")
    private Integer runningTime;
    @JsonProperty("dateReleased")
    private String dateReleased;
    @JsonProperty("basedOn")
    private String basedOn;
    @JsonProperty("movieLanguage")
    private MovieLanguage movieLanguage;
    @JsonProperty("cast")
    private List<Cast> cast = null;
    @JsonProperty("director")
    private List<String> director = null;
    @JsonProperty("producer")
    private List<String> producer = null;
    @JsonProperty("musicDirector")
    private List<String> musicDirector = null;
    @JsonProperty("cinematography")
    private List<String> cinematography = null;
    @JsonProperty("distributedBy")
    private List<DistributedBy> distributedBy = null;
    @JsonProperty("productionCompany")
    private List<String> productionCompany = null;
    @JsonProperty("movie")
    private Movie_ movie;
    @JsonProperty("genres")
    private List<String> genres = null;
    @JsonProperty("cost")
    private Cost cost;

}
