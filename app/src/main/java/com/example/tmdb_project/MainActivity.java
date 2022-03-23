package com.example.tmdb_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tmdb_project.Auth.SignInFragement;
import com.example.tmdb_project.Auth.SignUpFragement;

import java.io.Console;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    RecyclerView trendingRecyclerView;
    private String s1[], s2[];
    private int image[] = {};

    private TextView link;
    private Button connexion_btn;
    private EditText id_txt;
    private EditText password_txt;
    private Fragment activeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_trending_page_fragment);

        //Setup recyclerViews de la page trending
        trendingRecyclerView = findViewById(R.id.trending_recycler_view);
        s1 = getResources().getStringArray(R.array.recyclerView_title);
        s2 = getResources().getStringArray(R.array.recyclerView_date_sortie);
        TrendingAdapter trendingAdapter = new TrendingAdapter(this,s1,s2/*,image*/);
        trendingRecyclerView.setAdapter(trendingAdapter);
        trendingRecyclerView.setLayoutManager(new LinearLayoutManager(this));
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