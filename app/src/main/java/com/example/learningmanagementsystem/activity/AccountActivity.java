package com.example.learningmanagementsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.learningmanagementsystem.R;
import com.example.learningmanagementsystem.utilidades.KeyboardUtil;

public class AccountActivity extends Fragment {
    TextView textViewStudentProfile, textViewChangePassword, textViewLogout;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AccountActivity() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static InteractionActivity newInstance(String param1, String param2) {
        InteractionActivity fragment = new InteractionActivity();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_account_section, container, false);
        getFormWidgets(view);
        addEvent();
        return view;
    }

    private void addEvent() {
        textViewStudentProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent  = new Intent(getContext(), ProfileActivity.class);
                intent.putExtra("classCourse", "Giao tiếp cơ bản");
                startActivity(intent);
            }
        });
//        textViewChangePassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent  intent  = new Intent(getContext(), AnnouncementsActivity.class);
//                startActivity(intent);
//            }
//        });
        textViewLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent  = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getFormWidgets(View view) {
        textViewStudentProfile = view.findViewById(R.id.textViewStudentProfile);
        textViewChangePassword = view.findViewById(R.id.textViewChangePassword);
        textViewLogout = view.findViewById(R.id.textViewLogout);


    }
}
