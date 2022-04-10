package com.example.tmdb_project.ui.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WatchingMovieDetailsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public WatchingMovieDetailsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is watching movie fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}