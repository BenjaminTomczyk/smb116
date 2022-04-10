package com.example.tmdb_project.ui.watchlist;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.example.tmdb_project.Models.Movie;
import com.example.tmdb_project.OnItemClickListener;
import com.example.tmdb_project.R;
import com.example.tmdb_project.TrendingAdapter;
import com.example.tmdb_project.WatchingAdapter;
import com.example.tmdb_project.databinding.FragmentWatchingBinding;
import com.example.tmdb_project.ui.movie.MovieDetailsFragment;

import java.util.ArrayList;

public class WatchingFragment extends Fragment implements OnItemClickListener {

    private FragmentWatchingBinding binding;
    private RecyclerView watchingRecyclerView;
    private WatchingAdapter watchingAdapter;

    private ArrayList<Movie> arrayMovie;

    public WatchingFragment(){
        super(R.layout.fragment_watching);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_watching,container,false);
        watchingRecyclerView = view.findViewById(R.id.watching_recycler_view);

        arrayMovie = new ArrayList<Movie>();

        //TEST recycler view
        Movie test = new Movie();
        test.name = "test title";
        test.release_date = "test date";
        test.vote_average = 533.2;
        test.overview = "test resume";
        arrayMovie.add(test);
        //

        watchingAdapter = new WatchingAdapter(arrayMovie,this);
        watchingRecyclerView.setAdapter(watchingAdapter);
        watchingRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClick(Movie movie) {
        Bundle args = new Bundle();
        args.putSerializable("clicked_movie", movie);

        getActivity().getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(android.R.id.content, MovieDetailsFragment.class, args)
                .addToBackStack(null)
                .commit();
    }
}