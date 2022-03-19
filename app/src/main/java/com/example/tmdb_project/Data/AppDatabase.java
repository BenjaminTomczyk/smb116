package com.example.tmdb_project.Data;

import android.app.Application;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.tmdb_project.MainActivity;

@Database(entities = {User.class}, version = 1, exportSchema = true)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    private static AppDatabase INSTANCE;

    public static AppDatabase getDbInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "smb116_db").build();
        }
        return INSTANCE;
    }

}
