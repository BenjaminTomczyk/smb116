package com.example.tmdb_project.ui.watchlist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WatchingViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public WatchingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is watching fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}