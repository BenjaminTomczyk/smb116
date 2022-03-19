package com.example.tmdb_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tmdb_project.Data.AppDatabase;

import java.io.Console;

public class MainActivity extends AppCompatActivity {

    private TextView link;
    private Button connexion_btn;
    private EditText id_txt;
    private EditText password_txt;
    private Fragment activeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart(){
        super.onStart();

        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "smb116_db").build();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}