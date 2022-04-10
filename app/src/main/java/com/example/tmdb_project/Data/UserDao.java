package com.example.tmdb_project.Data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tmdb_project.Models.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE email = :userEmail")
    User loadByEmail(String userEmail);

    @Query("SELECT Count(*) FROM User WHERE email = :email AND password = :password")
    Integer checkUser(String email, String password);

    @Update
    void updateUser(User user);

    @Insert
    void insertUser(User user);
}
