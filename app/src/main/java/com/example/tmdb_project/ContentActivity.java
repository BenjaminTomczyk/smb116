package com.example.tmdb_project;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ContentActivity extends AppCompatActivity {

    RecyclerView trendingRecyclerView;
    private String s1[], s2[];
    private int image[] = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_trending_page_fragment);

        //Setup recyclerView de la page trending
        trendingRecyclerView = findViewById(R.id.trending_recycler_view);
        s1 = getResources().getStringArray(R.array.recyclerView_title);
        s2 = getResources().getStringArray(R.array.recyclerView_date_sortie);
        TrendingAdapter trendingAdapter = new TrendingAdapter(this,s1,s2/*,image*/);
        trendingRecyclerView.setAdapter(trendingAdapter);
        trendingRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
