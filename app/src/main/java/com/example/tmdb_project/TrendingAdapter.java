package com.example.tmdb_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TrendingAdapter extends RecyclerView.Adapter<TrendingAdapter.TrendViewHolder> {

    String data_title[], data_date[];
    int img[];
    Context context;

    public TrendingAdapter(String s1[], String s2[]/*, int image[]*/){
        data_title = s1;
        data_date = s2;
        //img = image;
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
        holder.txt_title.setText(data_title[position]);
        holder.txt_date_sortie.setText(data_date[position]);
        //holder.img_miniature.setImageResource(img[position]);
    }

    @Override
    public int getItemCount() {
        return data_title.length;
    }

    public class TrendViewHolder extends RecyclerView.ViewHolder{

        TextView txt_title, txt_date_sortie;
        //ImageView img_miniature;

        public TrendViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_title = itemView.findViewById(R.id.recycler_title);
            txt_date_sortie = itemView.findViewById(R.id.recycler_date_sortie);
            //img_miniature = itemView.findViewById(R.id.recycler_image);
        }
    }
}
