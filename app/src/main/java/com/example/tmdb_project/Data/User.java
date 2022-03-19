package com.example.tmdb_project.Data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "user")
public class User {
    @PrimaryKey @NonNull
    String email;

    @ColumnInfo(name = "password")
    String password;

    @ColumnInfo(name = "birthdate")
    Long birthdate;

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String pass){
        this.password = pass;
    }

    public void setBirthdate(Long birth){
        this.birthdate = birth;
    }

    public String getEmail(){ return this.email; }

    public String getPassword() { return this.password; }

    public Long getBirthdate() { return this.birthdate; }
}
