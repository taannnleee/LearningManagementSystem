package com.example.learningmanagementsystem.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.learningmanagementsystem.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InteractionActivity#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InteractionActivity extends Fragment {
    private ImageView imgVToeic, imgVIelts, imgVCommunication;
    private ImageView imgVAssignments, imgVHomework, imgVAbsence;
    private  ImageView imgVPosts, imgVAnnouncements, imgVSupports, imageViewAbsence;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InteractionActivity() {
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
        View view = inflater.inflate(R.layout.activity_interaction, container, false);
        getFormWidgets(view);
        addEvent();
        return view;
    }

    private void addEvent() {
        // cac ham chuyen trang
        imgVToeic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(com.example.learningmanagementsystem.activity.InteractionActivity.this, "Success", Toast.LENGTH_SHORT).show();
                Intent intent  = new Intent(getContext(), ListClassesShowStudent.class);
                intent.putExtra("classCourse", "TOEIC");
                startActivity(intent);
            }
        });
        imgVIelts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(com.example.learningmanagementsystem.activity.InteractionActivity.this, "Success", Toast.LENGTH_SHORT).show();
                Intent  intent  = new Intent(getContext(), ListClassesShowStudent.class);
                intent.putExtra("classCourse", "IELTS");
                startActivity(intent);
            }
        });
        imgVCommunication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(com.example.learningmanagementsystem.activity.InteractionActivity.this, "Success", Toast.LENGTH_SHORT).show();
                Intent  intent  = new Intent(getContext(), ListClassesShowStudent.class);
                intent.putExtra("classCourse", "Giao tiếp cơ bản");
                startActivity(intent);
            }
        });
        imgVAnnouncements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent  intent  = new Intent(getContext(), AnnouncementsActivity.class);
                startActivity(intent);
            }
        });
        imgVSupports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent  intent  = new Intent(getContext(), SupportActivity.class);
                startActivity(intent);
            }
        });
        imageViewAbsence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent  intent  = new Intent(getContext(), AbsenceRequestActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getFormWidgets(View view) {
        imgVToeic = view.findViewById(R.id.imageViewToeic);
        imgVIelts = view.findViewById(R.id.imageViewIelts);
        imgVCommunication = view.findViewById(R.id.imageViewCommunication);
        imageViewAbsence = view.findViewById(R.id.imageViewAbsence);
        imgVAnnouncements = view.findViewById(R.id.imageViewAnnouncements);
        imgVSupports = view.findViewById(R.id.imageViewSupports);


    }

}