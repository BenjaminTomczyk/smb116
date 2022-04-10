package com.example.tmdb_project.Models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@Entity(tableName = "movie")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;

    @JsonProperty("title")
    public String name;

    @ColumnInfo(name = "release_date")
    @JsonProperty("release_date")
    public String release_date;

    @ColumnInfo(name = "poster_path")
    @JsonProperty("poster_path")
    public String poster_path;

    @ColumnInfo(name = "vote_average")
    @JsonProperty("vote_average")
    public Double vote_average;

    @ColumnInfo(name = "overview")
    @JsonProperty("overview")
    public String overview;

    @ColumnInfo(name = "backdrop_path")
    @JsonProperty("backdrop_path")
    public String backdrop_path;

    public int userMovieId;

    public String getName(){ return this.name; }

    public String getReleaseDate() { return this.release_date; }

    public String getPosterPath() { return this.poster_path; }

    public Double getVoteAverage(){ return this.vote_average; }

    public String getOverview() { return this.overview; }

    public String getBackdropPath() { return this.backdrop_path; }
}