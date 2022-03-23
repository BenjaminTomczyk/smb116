package com.example.tmdb_project;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
import java.io.IOException;

public class UserProfileFragment extends Fragment{


    Uri ImageUrl;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button B_take_picture;
    private Button B_choose_picture;
    private Button B_save_password;
    private EditText ET_password;
    private EditText ET_password_confirm;
    private ImageView IV_avatar;
    private static final int TAKE_PICTURE = 1;
    private static final int CHOOSE_PICTURE = 2;

    public UserProfileFragment() {
        super(R.layout.fragment_profil);
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserProfileFragment newInstance(String param1, String param2) {
        UserProfileFragment fragment = new UserProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profil, container, false);

        B_take_picture = view.findViewById(R.id.B_take_picture);
        B_choose_picture = view.findViewById(R.id.B_choose_picture);
        B_save_password = view.findViewById(R.id.B_save_password);
        ET_password = view.findViewById(R.id.ET_password);
        ET_password_confirm = view.findViewById(R.id.ET_password_confirm);
        IV_avatar = view.findViewById(R.id.IV_avatar);

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

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == TAKE_PICTURE && resultCode == getActivity().RESULT_OK) {
            super.onActivityResult(requestCode, resultCode, data);
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            IV_avatar.setImageBitmap(photo);
        }

        if(requestCode == CHOOSE_PICTURE && resultCode == getActivity().RESULT_OK) {
            ImageUrl = data.getData();
            try{
                Bitmap photo = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), ImageUrl);
                IV_avatar.setImageBitmap(photo);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
