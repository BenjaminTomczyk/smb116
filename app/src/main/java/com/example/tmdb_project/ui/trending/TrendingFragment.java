package com.example.tmdb_project.ui.trending;

import static com.android.volley.Response.*;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tmdb_project.Models.Movie;
import com.example.tmdb_project.OnItemClickListener;
import com.example.tmdb_project.R;
import com.example.tmdb_project.TrendingAdapter;
import com.example.tmdb_project.ui.movie.TrendingMovieDetailsFragment;
import com.example.tmdb_project.ui.movie.WatchingMovieDetailsFragment;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

public class TrendingFragment extends Fragment implements OnItemClickListener{

    private RecyclerView trendingRecyclerView;
    private TrendingAdapter trendingAdapter;
    private RequestQueue queue;
    private SearchView sv;

    private ArrayList<Movie> arrayMovie;
    private ArrayList<Movie> filterArrayMovie;

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
        sv = view.findViewById(R.id.trending_search_view);
        queue = Volley.newRequestQueue(view.getContext());

        arrayMovie = new ArrayList<Movie>();
        filterArrayMovie = new ArrayList<Movie>();

        trendingAdapter = new TrendingAdapter(filterArrayMovie,this);
        trendingRecyclerView.setAdapter(trendingAdapter);
        trendingRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filter(s);
                trendingAdapter.notifyDataSetChanged();
                return false;
            }
        });


        try {
            GetRequestAPI();

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return view;
    }

    private void GetRequestAPI() throws JsonProcessingException {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, (Listener) response -> {

            try {
                JSONObject jsonObject = new JSONObject(response.toString());
                JSONArray jsonArray = jsonObject.getJSONArray("results");

                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject movieObject = jsonArray.getJSONObject(i);

                    String name = movieObject.getString("title");
                    String release_date = movieObject.getString("release_date");
                    String poster_path = movieObject.getString("poster_path");
                    Double vote_average = movieObject.getDouble("vote_average");
                    String overview = movieObject.getString("overview");
                    String backdrop_path = movieObject.getString("backdrop_path");

                    Movie movie = new Movie();
                    movie.name = name;
                    movie.release_date = release_date;
                    movie.poster_path = imgUrl + poster_path;
                    movie.vote_average = vote_average;
                    movie.overview = overview;
                    movie.backdrop_path = imgUrl + backdrop_path;


                    arrayMovie.add(movie);
                    filterArrayMovie.add(movie);
                }

                trendingAdapter.notifyDataSetChanged();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        },error -> Log.e("JSONObject: ", error.toString()));

        queue.add(stringRequest);
    }

    @Override
    public void onItemClick(Movie movie) {
        Bundle args = new Bundle();
        args.putSerializable("clicked_movie", movie);

        getActivity().getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(android.R.id.content, TrendingMovieDetailsFragment.class, args)
                .addToBackStack("trending")
                .commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        trendingRecyclerView.setAdapter(null);
        trendingAdapter = null;
        trendingRecyclerView = null;
    }

    private void filter(String s) {
        filterArrayMovie = new ArrayList<Movie>();
        for(Movie movie : arrayMovie){
            if(movie.name.toLowerCase().contains(s.toLowerCase())){
                filterArrayMovie.add(movie);
            }
        }

        trendingAdapter.setArrayMovie(filterArrayMovie);
        trendingAdapter.notifyDataSetChanged();
    }
}