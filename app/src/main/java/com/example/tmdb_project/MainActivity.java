package com.example.tmdb_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tmdb_project.Auth.SignInFragement;
import com.example.tmdb_project.Auth.SignUpFragement;

import java.io.Console;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    RecyclerView trendingRecyclerView;
    private String s1[], s2[];
    private int image[] = {};

    private TextView link;
    private Button connexion_btn;
    private EditText id_txt;
    private EditText password_txt;
    private Fragment activeFragment;

    //User profile
    private Button B_take_picture = findViewById(R.id.B_take_picture);
    private Button B_choose_picture = findViewById(R.id.B_choose_picture);
    private Button B_save_password = findViewById(R.id.B_save_password);
    private EditText ET_password = findViewById(R.id.ET_password);
    private EditText ET_password_confirm = findViewById(R.id.ET_password_confirm);
    private ImageView IV_avatar = findViewById(R.id.IV_avatar);
    private static final int TAKE_PICTURE = 1;
    private static final int CHOOSE_PICTURE = 2;
    Uri ImageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_trending_page_fragment);

        //Setup recyclerViews de la page trending
        trendingRecyclerView = findViewById(R.id.trending_recycler_view);
        s1 = getResources().getStringArray(R.array.recyclerView_title);
        s2 = getResources().getStringArray(R.array.recyclerView_date_sortie);
        TrendingAdapter trendingAdapter = new TrendingAdapter(this,s1,s2/*,image*/);
        trendingRecyclerView.setAdapter(trendingAdapter);
        trendingRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        B_take_picture.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent open_camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(open_camera, TAKE_PICTURE);
            }
        });

        B_choose_picture.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent open_gallery = new Intent();
                open_gallery.setType("image/*");
                open_gallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(open_gallery, "Select a picture"),CHOOSE_PICTURE);
            }
        });

        B_save_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ET_password != null
                && ET_password_confirm != null
                && ET_password.getText().length() > 0
                && ET_password_confirm.getText().length() > 0
                && ET_password == ET_password_confirm){
                    //@TODO change password
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == TAKE_PICTURE && resultCode == RESULT_OK) {
                super.onActivityResult(requestCode, resultCode, data);
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                IV_avatar.setImageBitmap(photo);
        }

        if(requestCode == CHOOSE_PICTURE && resultCode == RESULT_OK) {
            ImageUrl = data.getData();
            try{
                Bitmap photo = MediaStore.Images.Media.getBitmap(getContentResolver(), ImageUrl);
                IV_avatar.setImageBitmap(photo);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onStart(){
        super.onStart();

        Toast.makeText(getApplicationContext(), "START", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "RESUME", Toast.LENGTH_SHORT).show();
    }
}