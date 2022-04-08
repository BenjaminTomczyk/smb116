package com.example.tmdb_project;

public class Movie {

    public String name;
    public String date;

    public Movie(String title,String release)
    {
        this.name = title;
        this.date = release;
    }

    public String getTitle() {
        return name;
    }

    public String getDate() {
        return date;
    }
}
