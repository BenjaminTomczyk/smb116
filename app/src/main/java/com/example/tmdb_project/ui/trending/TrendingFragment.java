package com.example.tmdb_project.ui.trending;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tmdb_project.AppActivity;
import com.example.tmdb_project.MainActivity;
import com.example.tmdb_project.Movie;
import com.example.tmdb_project.R;
import com.example.tmdb_project.TrendingAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class TrendingFragment extends Fragment {

    private RecyclerView trendingRecyclerView;
    private TrendingAdapter trendingAdapter;
    private RequestQueue queue;

    private String[] s1;
    private String[] s2;
    private String[] s3;
    private String[] s4;

    private static String key = "2b275544b21406e66bc5310fb0bbb38a";
    private static String url = "https://api.themoviedb.org/3/trending/movie/week?api_key="+key;

    public TrendingFragment(){
        super(R.layout.fragment_trending);
    }

    @Override
    public void onCreate(Bundle savedInstanceState){ super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_trending,container,false);
        trendingRecyclerView = view.findViewById(R.id.trending_recycler_view);
        queue = Volley.newRequestQueue(view.getContext());
        getdata();

        //Valeurs de test définies dans strings.xml
        s1 = getResources().getStringArray(R.array.recyclerView_title);
        s2 = getResources().getStringArray(R.array.recyclerView_date_sortie);
        //int images[] = {};

        trendingAdapter = new TrendingAdapter(s3,s4/*,image*/);
        trendingRecyclerView.setAdapter(trendingAdapter);
        trendingRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        return view;
    }

    private void getdata()
    {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, response -> {
            JSONArray jsonArray = response;
            try {
                for(int i=0;i<jsonArray.length();i++)
                {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String name = jsonObject.getString("title");
                    String date = jsonObject.getString("release_date");
                    //mList.add(new Movie(name,date));
                    s3[i] = name;
                    s4[i] = date;
                }
                trendingAdapter.notifyDataSetChanged();//To prevent app from crashing when updating
                //UI through background Thread
            }
            catch (Exception w)
            {
                //Toast.makeText(MainActivity.this,w.getMessage(),Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        queue.add(jsonArrayRequest);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        trendingRecyclerView.setAdapter(null);
        trendingAdapter = null;
        trendingRecyclerView = null;
    }
}