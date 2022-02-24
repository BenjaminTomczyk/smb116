package com.example.tmdb_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tmdb_project.Auth.SignInFragement;
import com.example.tmdb_project.Auth.SignUpFragement;

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

        Toast.makeText(getApplicationContext(), "START", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "RESUME", Toast.LENGTH_SHORT).show();
    }
}