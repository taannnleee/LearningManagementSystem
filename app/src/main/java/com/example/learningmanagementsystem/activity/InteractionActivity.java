package com.example.learningmanagementsystem.activity;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.learningmanagementsystem.R;

public class InteractionActivity extends AppCompatActivity {
    private ImageView imgVSchedules, imgVClasses, imgVCompenstate;
    private ImageView imgVAssignments, imgVHomework, imgVAbsence;
    private  ImageView imgVPosts, imgVAnnouncements, imgVSupports;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interaction);
        getFormWidgets(); // Call this method first to initialize widgets
        addEvent();
    }

    private void addEvent() {
        // cac ham chuyen trang
    }

    private void getFormWidgets() {
        imgVSchedules = findViewById(R.id.imageViewSchedules);
        imgVClasses = findViewById(R.id.imageViewClasses);
        imgVCompenstate = findViewById(R.id.imageViewCompensate);
        imgVAssignments = findViewById(R.id.imageViewAssignments);
        imgVHomework = findViewById(R.id.imageViewHomework);
        imgVAbsence = findViewById(R.id.imageViewAbsence);
        imgVPosts = findViewById(R.id.imageViewPosts);
        imgVAnnouncements = findViewById(R.id.imageViewAnnouncements);
        imgVSupports = findViewById(R.id.imageViewSupports);

    }
}
