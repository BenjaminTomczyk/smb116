package com.example.tmdb_project.ui.trending;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tmdb_project.R;
import com.example.tmdb_project.TrendingAdapter;

public class TrendingFragment extends Fragment {

    private RecyclerView trendingRecyclerView;
    private TrendingAdapter trendingAdapter;

    public TrendingFragment(){
        super(R.layout.fragment_trending);
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_trending,container,false);
        trendingRecyclerView = view.findViewById(R.id.trending_recycler_view);

        //Valeurs de test définies dans strings.xml
        String s1[] = getResources().getStringArray(R.array.recyclerView_title);
        String s2[] = getResources().getStringArray(R.array.recyclerView_date_sortie);
        //int images[] = {};

        trendingAdapter = new TrendingAdapter(s1,s2/*,image*/);
        trendingRecyclerView.setAdapter(trendingAdapter);
        trendingRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        trendingRecyclerView.setAdapter(null);
        trendingAdapter = null;
        trendingRecyclerView = null;
    }
}