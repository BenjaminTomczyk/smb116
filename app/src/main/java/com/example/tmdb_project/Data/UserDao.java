package com.example.tmdb_project.Data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE email = :userEmail")
    User loadByEmail(String userEmail);

    @Query("SELECT Count(*) FROM User WHERE email = :email AND password = :password")
    Integer checkUser(String email, String password);

    @Insert
    void insertUser(User user);
}
