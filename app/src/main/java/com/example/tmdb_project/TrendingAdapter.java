package com.example.tmdb_project;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tmdb_project.Models.Movie;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class TrendingAdapter extends RecyclerView.Adapter<TrendingAdapter.TrendViewHolder> {

    ArrayList<Movie> arrayMovie;

    String data_title[], data_date[];
    int img[];
    Context context;

    public TrendingAdapter(ArrayList<Movie> movies){
        arrayMovie = movies;
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
        holder.txt_title.setText(arrayMovie.get(position).name);
        holder.txt_date_sortie.setText(arrayMovie.get(position).release_date);
        Picasso.get().load(arrayMovie.get(position).poster_path).into(holder.img_miniature);
    }

    @Override
    public int getItemCount() {
        return arrayMovie.size();
    }

    public class TrendViewHolder extends RecyclerView.ViewHolder{

        TextView txt_title, txt_date_sortie;
        ImageView img_miniature;

        public TrendViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_title = itemView.findViewById(R.id.recycler_title);
            txt_date_sortie = itemView.findViewById(R.id.recycler_date_sortie);
            img_miniature = itemView.findViewById(R.id.recycler_image);
        }
    }
}
