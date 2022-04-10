package com.example.tmdb_project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tmdb_project.Models.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TrendingAdapter extends RecyclerView.Adapter<TrendingAdapter.TrendViewHolder> {

    ArrayList<Movie> arrayMovie;
    private final OnItemClickListener listener;

    public TrendingAdapter(ArrayList<Movie> movies,OnItemClickListener listener){
        arrayMovie = movies;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TrendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.trending_recycler_view_item, parent, false);
        return new TrendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrendViewHolder holder, int position) {
        Movie movie = arrayMovie.get(position);

        holder.txt_title.setText(movie.name);
        holder.txt_date_sortie.setText(movie.release_date);
        Picasso.get().load(movie.poster_path).into(holder.img_miniature);
        holder.cardView.setOnClickListener(view -> listener.onItemClick(movie));
    }


    @Override
    public int getItemCount() {
        return arrayMovie.size();
    }

    public class TrendViewHolder extends RecyclerView.ViewHolder{

        TextView txt_title, txt_date_sortie;
        ImageView img_miniature;
        CardView cardView;

        public TrendViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_title = itemView.findViewById(R.id.trending_recycler_title);
            txt_date_sortie = itemView.findViewById(R.id.trending_recycler_date_sortie);
            img_miniature = itemView.findViewById(R.id.trending_recycler_image);
            cardView = itemView.findViewById(R.id.trending_recycler_item);
        }
    }
}
