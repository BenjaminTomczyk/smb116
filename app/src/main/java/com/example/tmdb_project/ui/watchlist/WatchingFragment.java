package com.example.tmdb_project.ui.watchlist;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.tmdb_project.Data.AppDatabase;
import com.example.tmdb_project.Models.Movie;
import com.example.tmdb_project.OnItemClickListener;
import com.example.tmdb_project.R;
import com.example.tmdb_project.WatchingAdapter;
import com.example.tmdb_project.databinding.FragmentWatchingBinding;
import com.example.tmdb_project.ui.movie.TrendingMovieDetailsFragment;
import com.example.tmdb_project.ui.movie.WatchingMovieDetailsFragment;

import java.util.ArrayList;

public class WatchingFragment extends Fragment implements OnItemClickListener {

    private FragmentWatchingBinding binding;
    private RecyclerView watchingRecyclerView;
    private WatchingAdapter watchingAdapter;

    private AppDatabase db;

    private ArrayList<Movie> arrayMovie;

    public WatchingFragment(){
        super(R.layout.fragment_watching);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_watching,container,false);
        watchingRecyclerView = view.findViewById(R.id.watching_recycler_view);

        db = Room.databaseBuilder(getActivity().getApplicationContext(), AppDatabase.class, "smb116_db").allowMainThreadQueries().build();


        Integer userId = getActivity().getSharedPreferences("MyPref", 0).getInt("userId", -1);

        arrayMovie = new ArrayList<Movie>(db.movieDao().getAll(userId));

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
                .replace(android.R.id.content, WatchingMovieDetailsFragment.class, args)
                .addToBackStack("watching")
                .commit();
    }
}