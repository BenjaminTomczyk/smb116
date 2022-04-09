package com.example.tmdb_project.ui.movie;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.tmdb_project.Auth.SignUpFragement;
import com.example.tmdb_project.Models.Movie;
import com.example.tmdb_project.R;
import com.example.tmdb_project.ui.trending.TrendingFragment;

public class MovieDetailsFragment extends Fragment {

    private Movie movie;
    private Button back;

    public MovieDetailsFragment(){
        super(R.layout.fragment_movie_details);
    }

    @Override
    public void onCreate(Bundle savedInstanceState){ super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_movie_details,container,false);

        TextView textView_title = view.findViewById(R.id.textView_title);
        TextView textView_date = view.findViewById(R.id.textView_date);
        TextView textView_score = view.findViewById(R.id.textView_score);
        TextView textView_overview = view.findViewById(R.id.textView_overview);

        Bundle bundle = getArguments();
        Movie movie = (Movie) bundle.getSerializable("clicked_movie");

        this.movie = movie;

        String title = "Titre\n\n" + movie.name;
        String date = "Date de sortie\n\n" + movie.release_date;
        String score = "Note moyenne\n\n" + movie.vote_average;
        String overview = "Résumé\n\n" + movie.overview;

        textView_title.setText(title);
        textView_date.setText(date);
        textView_score.setText(score);
        textView_overview.setText(overview);

        back = view.findViewById(R.id.button_back);
        back.setOnClickListener(view1 -> getActivity().getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(android.R.id.content, TrendingFragment.class, null)
                .addToBackStack(null)
                .commit());

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}