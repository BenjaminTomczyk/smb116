package com.example.tmdb_project.ui.profil;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import com.example.tmdb_project.Data.AppDatabase;
import com.example.tmdb_project.Data.User;
import com.example.tmdb_project.R;
import com.example.tmdb_project.databinding.FragmentProfilBinding;

import java.io.IOException;

public class ProfilFragment extends Fragment {

    private FragmentProfilBinding binding;
    private Uri ImageUrl;

    private Button b_take_picture;
    private Button b_choose_picture;
    private Button b_save_password;
    private EditText et_password;
    private EditText et_password_confirm;
    private ImageView iv_avatar;
    private static final int TAKE_PICTURE = 1;
    private static final int CHOOSE_PICTURE = 2;

    private AppDatabase db;

    public ProfilFragment(){
        super(R.layout.fragment_profil);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfilViewModel profilViewModel =
                new ViewModelProvider(this).get(ProfilViewModel.class);

        binding = FragmentProfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //View view = inflater.inflate(R.layout.fragment_profil, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        b_take_picture = view.findViewById(R.id.B_take_picture);
        b_choose_picture = view.findViewById(R.id.B_choose_picture);
        b_save_password = view.findViewById(R.id.B_save_password);
        et_password = view.findViewById(R.id.ET_password);
        et_password_confirm = view.findViewById(R.id.ET_password_confirm);
        iv_avatar = view.findViewById(R.id.IV_avatar);

        b_take_picture.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent open_camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(open_camera, TAKE_PICTURE);
            }
        });

        b_choose_picture.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent open_gallery = new Intent();
                open_gallery.setType("image/*");
                open_gallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(open_gallery, "Select a picture"),CHOOSE_PICTURE);
            }
        });

        b_save_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_password != null
                        && et_password_confirm != null
                        && et_password.getText().length() > 0
                        && et_password_confirm.getText().length() > 0
                        && et_password == et_password_confirm){

                    //db = Room.databaseBuilder(getActivity().getApplicationContext(), AppDatabase.class, "smb116_db").allowMainThreadQueries().build();
                    //db.
                    //User user = db.userDao().loadByEmail(email);
                    //db.userDao().updateUser(user);
                    //@TODO change password
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == TAKE_PICTURE) {
            super.onActivityResult(requestCode, resultCode, data);
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            iv_avatar.setImageBitmap(photo);
        }

        //if(requestCode == CHOOSE_PICTURE && resultCode == getActivity().RESULT_OK) {
        if(requestCode == CHOOSE_PICTURE) {
            ImageUrl = data.getData();
            try{
                Bitmap photo = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), ImageUrl);
                iv_avatar.setImageBitmap(photo);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}