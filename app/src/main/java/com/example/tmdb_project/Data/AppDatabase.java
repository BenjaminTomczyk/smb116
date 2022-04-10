package com.example.tmdb_project.Data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.tmdb_project.Models.Movie;
import com.example.tmdb_project.Models.User;

@Database(entities = {User.class, Movie.class}, version = 1, exportSchema = true)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract MovieDao movieDao();

    private static AppDatabase INSTANCE = null;

    public static AppDatabase getDbInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "smb116_db").fallbackToDestructiveMigration().build();
        }
        return INSTANCE;
    }

}
