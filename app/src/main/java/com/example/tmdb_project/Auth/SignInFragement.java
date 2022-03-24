package com.example.tmdb_project.Auth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tmdb_project.AppActivity;
import com.example.tmdb_project.ContentActivity;
import com.example.tmdb_project.Data.AppDatabase;
import com.example.tmdb_project.MainActivity;
import com.example.tmdb_project.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignInFragement#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignInFragement extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView link;
    private TextView emailInput;
    private TextView passwordInput;
    private Button btnConnec;

    private AppDatabase db;


    public SignInFragement() {
        super(R.layout.fragment_sign_in_fragement);
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignInFragement.
     */
    // TODO: Rename and change types and number of parameters
    public static SignInFragement newInstance(String param1, String param2) {
        SignInFragement fragment = new SignInFragement();
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

        View view = inflater.inflate(R.layout.fragment_sign_in_fragement, container, false);
        link = (TextView) view.findViewById(R.id.signup_label);
        emailInput = view.findViewById(R.id.id_txt);
        passwordInput = view.findViewById(R.id.password_txt);
        btnConnec = (Button) view.findViewById(R.id.connexion_btn);

        db = Room.databaseBuilder(getActivity().getApplicationContext(), AppDatabase.class, "smb116_db").allowMainThreadQueries().build();


        Bundle args = getArguments();

        if(args != null){
            System.out.println(args.getString("email"));
            System.out.println(args.getString("password"));

            emailInput.setText(args.getString("email"));
            passwordInput.setText(args.getString("password"));
        }

        link.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragment_container_view_auth, SignUpFragement.class, null)
                        .addToBackStack(null)
                        .commit();
            }
        });

        btnConnec.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(emailInput.getText().toString() != "" && passwordInput.getText().toString() != ""){
                    Integer userExist = db.userDao().checkUser(emailInput.getText().toString(), passwordInput.getText().toString());

                    if(userExist > 0){
                        Toast.makeText(getActivity().getApplicationContext(), "Le compte existe !", Toast.LENGTH_SHORT).show();

                        SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("MyPref", 0);
                        SharedPreferences.Editor editor = pref.edit();

                        editor.putString("email", emailInput.getText().toString());
                        editor.putString("password", passwordInput.getText().toString());
                        editor.commit();

                        //TODO : Connection
                        Intent contentIntent = new Intent(getActivity(), AppActivity.class);
                        startActivity(contentIntent);

                    }
                    else {
                        Toast.makeText(getActivity().getApplicationContext(), "Les informations de connexion sont invalides.", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getActivity().getApplicationContext(), "Merci de renseigner une adresse email et un mot de passe pour vous connecter.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}