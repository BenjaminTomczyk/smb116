package com.example.tmdb_project.ui.trending;

import android.content.Context;
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

import com.example.tmdb_project.R;
import com.example.tmdb_project.TrendingAdapter;
import com.example.tmdb_project.databinding.FragmentTrendingBinding;

public class TrendingFragment extends Fragment {

    private FragmentTrendingBinding binding;
    private RecyclerView trendingRecyclerView;
    private String s1[], s2[];
    private int image[] = {};
    Context context;

    public TrendingFragment(){
        super(R.layout.fragment_trending);
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TrendingViewModel trendingViewModel =
                new ViewModelProvider(this).get(TrendingViewModel.class);

        binding = FragmentTrendingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        context = container.getContext();

        View view = inflater.inflate(R.layout.fragment_trending, container, false);

        trendingRecyclerView = view.findViewById(R.id.trending_recycler_view);
        s1 = getResources().getStringArray(R.array.recyclerView_title);
        s2 = getResources().getStringArray(R.array.recyclerView_date_sortie);
        TrendingAdapter trendingAdapter = new TrendingAdapter(context,s1,s2/*,image*/);
        trendingRecyclerView.setAdapter(trendingAdapter);
        trendingRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}