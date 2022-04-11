package com.example.tmdb_project.ui.movie;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tmdb_project.Auth.SignUpFragement;
import com.example.tmdb_project.Data.AppDatabase;
import com.example.tmdb_project.Models.Movie;
import com.example.tmdb_project.R;
import com.example.tmdb_project.ui.watchlist.WatchingFragment;
import com.squareup.picasso.Picasso;

public class WatchingMovieDetailsFragment extends Fragment {

    private Movie movie;
    private Button back;
    private Button shareButton;

    private Button deleteWatchlistButton;
    private AppDatabase db;

    public WatchingMovieDetailsFragment(){
        super(R.layout.fragment_watching_movie_details);
    }

    @Override
    public void onCreate(Bundle savedInstanceState){ super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_watching_movie_details,container,false);

        TextView textView_title = view.findViewById(R.id.textView_title);
        TextView textView_date = view.findViewById(R.id.textView_date);
        TextView textView_score = view.findViewById(R.id.textView_score);
        TextView textView_overview = view.findViewById(R.id.textView_overview);
        ImageView imageView_poster = view.findViewById(R.id.ImageView_poster);

        db = Room.databaseBuilder(getActivity().getApplicationContext(), AppDatabase.class, "smb116_db").allowMainThreadQueries().build();

        Bundle bundle = getArguments();
        Movie movie = (Movie) bundle.getSerializable("clicked_movie");

        this.movie = movie;

        String title = "TITRE\n\n" + movie.name;
        String date = "DATE DE SORTIE\n\n" + movie.release_date;
        String score = "NOTE MOYENNE\n\n" + movie.vote_average + " / 10";
        String overview = "RESUME\n\n" + movie.overview;

        textView_title.setText(title);
        textView_date.setText(date);
        textView_score.setText(score);
        textView_overview.setText(overview);
        Picasso.get().load(movie.backdrop_path).into(imageView_poster);

        shareButton = view.findViewById(R.id.share_button2);
        back = view.findViewById(R.id.button_back_watching);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager().getPrimaryNavigationFragment();
                FragmentManager manager = navHostFragment.getChildFragmentManager();
                WatchingFragment wf = WatchingFragment.createNewInstance();

                manager.beginTransaction().replace(navHostFragment.getId(), wf).commitAllowingStateLoss();

                getActivity().getSupportFragmentManager().popBackStack("watching",1);
            }
        });

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Le film \"" + movie.name + "\" est incroyable ! Il faut absolument que tu le regarde !");
                shareIntent.setType("text/plain");

                startActivity(shareIntent);
            }
        });


        deleteWatchlistButton = view.findViewById(R.id.button_delete_watchlist);

        Integer userId = getActivity().getSharedPreferences("MyPref", 0).getInt("userId", -1);

        deleteWatchlistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(db.movieDao().checkIfExist(movie.name, userId) > 0){

                    db.movieDao().deleteMovie(movie.name, userId);
                    Toast.makeText(getActivity(), "Le fim à été supprimé de votre watchlist.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getActivity(), "Ce film n'est plus dans votre watchlist.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}