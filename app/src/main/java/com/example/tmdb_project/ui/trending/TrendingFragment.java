package com.example.tmdb_project.ui.trending;

import static com.android.volley.Response.*;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tmdb_project.AppActivity;
import com.example.tmdb_project.MainActivity;
//import com.example.tmdb_project.Movie;
import com.example.tmdb_project.Models.Movie;
import com.example.tmdb_project.R;
import com.example.tmdb_project.TrendingAdapter;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class TrendingFragment extends Fragment {

    private RecyclerView trendingRecyclerView;
    private TrendingAdapter trendingAdapter;
    private RequestQueue queue;

    private ArrayList<Movie> arrayMovie;

    private static String key = "2b275544b21406e66bc5310fb0bbb38a";
    private static String url = "https://api.themoviedb.org/3/trending/movie/week?api_key="+key;
    private static String imgUrl = "https://image.tmdb.org/t/p/w200";

    public TrendingFragment(){
        super(R.layout.fragment_trending);
    }

    @Override
    public void onCreate(Bundle savedInstanceState){ super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_trending,container,false);
        trendingRecyclerView = view.findViewById(R.id.trending_recycler_view);
        queue = Volley.newRequestQueue(view.getContext());

        arrayMovie = new ArrayList<Movie>();

        trendingAdapter = new TrendingAdapter(arrayMovie);
        trendingRecyclerView.setAdapter(trendingAdapter);
        trendingRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        try {
            GetRequestAPI();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return view;
    }

    private void GetRequestAPI() throws JsonProcessingException {
        Log.i("TEST :", "GetRequestAPI");

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener() {

            @Override
            public void onResponse(Object response) {

                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    JSONArray jsonArray = jsonObject.getJSONArray("results");

                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject movieObject = jsonArray.getJSONObject(i);

                        String name = movieObject.getString("title");
                        String release_date = movieObject.getString("release_date");
                        String poster_path = movieObject.getString("poster_path");


                        Movie movie = new Movie();
                        movie.name = name;
                        movie.release_date = release_date;
                        movie.poster_path = imgUrl + poster_path;

                        arrayMovie.add(movie);
                    }

                    for(Movie mov : arrayMovie){
                        Log.i("RESULT: ", mov.name + mov.release_date);
                    }

                    trendingAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error){
                Log.e("JSONObject: ", error.toString());
            }
        });



        queue.add(stringRequest);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        trendingRecyclerView.setAdapter(null);
        trendingAdapter = null;
        trendingRecyclerView = null;
    }
}