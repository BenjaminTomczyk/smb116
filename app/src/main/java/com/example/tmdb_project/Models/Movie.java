package com.example.tmdb_project.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {

    @JsonProperty("title")
    public String name;

    @JsonProperty("release_date")
    public String release_date;

    @JsonProperty("poster_path")
    public String poster_path;
}
