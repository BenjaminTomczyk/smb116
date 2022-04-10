package com.example.tmdb_project.Data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tmdb_project.Models.Movie;
import com.example.tmdb_project.Models.User;

import java.util.List;

@Dao
public interface MovieDao {
    @Query("SELECT * FROM movie WHERE userMovieId = :userId")
    List<Movie> getAll(int userId);

    @Query("SELECT * FROM movie WHERE name = :titleMovie")
    Movie loadByTitle(String titleMovie);

    @Query("SELECT Count(*) FROM movie WHERE name = :titleMovie AND userMovieId = :userId")
    Integer checkIfExist(String titleMovie, int userId);

    @Update
    void updateMovie(Movie movie);

    @Insert
    void insertMovie(Movie movie);

    @Query("DELETE FROM movie WHERE name = :titleMovie AND userMovieId = :userId")
    void deleteMovie(String titleMovie, int userId);
}
