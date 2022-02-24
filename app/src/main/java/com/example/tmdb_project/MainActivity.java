package com.example.tmdb_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Console;

public class MainActivity extends AppCompatActivity {

    private TextView signUpLink;
    private Button connexion_btn;
    private EditText id_txt;
    private EditText password_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart(){
        super.onStart();

        signUpLink = (TextView) findViewById(R.id.signup_label);
        connexion_btn = (Button) findViewById(R.id.connexion_btn);
        id_txt = (EditText) findViewById(R.id.id_txt);
        password_txt = (EditText) findViewById(R.id.password_txt);

        signUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if(view == signUpLink){
                    Toast.makeText(getApplicationContext(), "TEST", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}