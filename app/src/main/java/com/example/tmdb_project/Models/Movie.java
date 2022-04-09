package com.example.tmdb_project.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie implements Serializable {

    @JsonProperty("title")
    public String name;

    @JsonProperty("release_date")
    public String release_date;

    @JsonProperty("poster_path")
    public String poster_path;

    @JsonProperty("vote_average")
    public Double vote_average;

    @JsonProperty("overview")
    public String overview;

}
