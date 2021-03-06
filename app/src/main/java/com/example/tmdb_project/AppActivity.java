package com.example.tmdb_project;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.tmdb_project.Data.AppDatabase;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.room.Room;

import com.example.tmdb_project.databinding.ActivityAppBinding;

public class AppActivity extends AppCompatActivity {

    private ActivityAppBinding binding;
    private static String email;
    private String password;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAppBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_trending, R.id.navigation_watchlist, R.id.navigation_profil)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_app);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "smb116_db").allowMainThreadQueries().build();

        email = pref.getString("email", null);
        password = pref.getString("password", null);
    }

    @Override
    protected void onStart(){
        super.onStart();

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "smb116_db").allowMainThreadQueries().build();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }
}