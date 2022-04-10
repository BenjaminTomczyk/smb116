package com.example.tmdb_project.ui.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TrendingMovieDetailsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public TrendingMovieDetailsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is trendings movie fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}