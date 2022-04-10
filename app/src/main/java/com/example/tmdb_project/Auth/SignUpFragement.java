package com.example.tmdb_project.Auth;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tmdb_project.Data.AppDatabase;
import com.example.tmdb_project.Models.User;
import com.example.tmdb_project.R;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUpFragement#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpFragement extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView link;
    private EditText emailInput;
    private EditText passwordInput;
    private Button saveButton;
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListerner;

    private AppDatabase db;

    public SignUpFragement() {
        super(R.layout.fragment_sign_in_fragement);
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUpFragement.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUpFragement newInstance(String param1, String param2) {
        SignUpFragement fragment = new SignUpFragement();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up_fragement, container, false);
        link = (TextView) view.findViewById(R.id.signin_label);

        link.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity().getApplicationContext(), "START", Toast.LENGTH_SHORT).show();

                getActivity().getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragment_container_view_auth, SignInFragement.class, null)
                        .addToBackStack(null)
                        .commit();
            }
        });

        db = Room.databaseBuilder(getActivity().getApplicationContext(), AppDatabase.class, "smb116_db").allowMainThreadQueries().build();

        emailInput = view.findViewById(R.id.email_txt_signup);
        passwordInput = view.findViewById(R.id.password_txt_signup);
        saveButton = view.findViewById(R.id.connexion_btn_signup);

        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                saveNewUser();
            }
        });

        mDisplayDate = view.findViewById(R.id.date_picker);
        mDisplayDate.setText("Date de naissance");


        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(getActivity(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListerner, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListerner = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                String vday;
                String vmonth;
                String vyear;

                vday = day < 9 ? "0" + String.valueOf(day) : String.valueOf(day);
                vmonth = month < 9 ? "0" + String.valueOf(month+1) : String.valueOf(month+1);
                vyear = year < 9 ? "0" + String.valueOf(year) : String.valueOf(year);

                String date = vday + "/" + vmonth + "/" + vyear;
                mDisplayDate.setText(date);
            }
        };


        /*User user = new User();
        user.setEmail("mail");
        user.setPassword("pass");
        user.setBirthdate(Long.parseLong("01012000"));*/

        //db.userDao().insertUser(user);

        return view;
    }

    private void saveNewUser(){

        if(!emailInput.getText().toString().contains("@")){
            Toast.makeText(getActivity().getApplicationContext(), "Merci de renseigner une adresse email valide.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(passwordInput.getText().toString().length() < 7){
            Toast.makeText(getActivity().getApplicationContext(), "Votre mot de passe doit avoir une taille minimum de 8 characters.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(mDisplayDate.getText().toString().contains("Date de naissance")){
            Toast.makeText(getActivity().getApplicationContext(), "Merci de selectionner une date de naissance.", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            if(!userExist(emailInput.getText().toString())){

                User user = new User();
                user.setEmail(emailInput.getText().toString());
                user.setPassword(passwordInput.getText().toString());
                user.setBirthdate(Long.parseLong(mDisplayDate.getText().toString().replace("/", "")));

                db.userDao().insertUser(user);


                //Retour au fragment connexion avec les infos d'inscriptions

                Bundle args = new Bundle();
                args.putString("email", emailInput.getText().toString());
                args.putString("password", passwordInput.getText().toString());

                getActivity().getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragment_container_view_auth, SignInFragement.class, args)
                        .addToBackStack(null)
                        .commit();
            }
            else {
                Toast.makeText(getActivity().getApplicationContext(), "Un compte avec cette adresse email existe déjà.", Toast.LENGTH_SHORT).show();
            }
        }
        catch(Exception exception) {
            throw exception;
        }
    }

    public boolean userExist(String email){
        User user = db.userDao().loadByEmail(email);
        if(user != null){
            return true;
        }
        return false;
    }
}