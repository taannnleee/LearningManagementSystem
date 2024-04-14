package com.example.learningmanagementsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.learningmanagementsystem.R;

public class InteractionAdminActivity extends AppCompatActivity {
    private ImageView imgVAddTeacher, imgVAddCourse, imgVConfirm, imgVPosts, imgVAnnouncements, imgVSupports;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interaction_admin);
        getFormWidgets(); // Call this method first to initialize widgets
        addEvent();
    }

    private void addEvent() {
        imgVAddTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterTeacherActivity.class);
                startActivity(intent);
            }
        });
        imgVAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreationClassActivity.class);
                startActivity(intent);
            }
        });
        imgVConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreationClassActivity.class);
                startActivity(intent);
            }
        });
        // Nhung trang nay chua co view
//        imgVPosts.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), CreationClassActivity.class);
//                startActivity(intent);
//            }
//        });
//        imgVAnnouncements.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), CreationClassActivity.class);
//                startActivity(intent);
//            }
//        });
//        imgVSupports.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), CreationClassActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    private void getFormWidgets() {
        imgVAddTeacher = findViewById(R.id.imageViewAddTeacher);
        imgVAddCourse = findViewById(R.id.imageViewAddCourse);
        imgVConfirm = findViewById(R.id.imageViewConfirm);
        imgVPosts = findViewById(R.id.imageViewPosts);
        imgVAnnouncements = findViewById(R.id.imageViewAnnouncements);
        imgVSupports = findViewById(R.id.imageViewSupports);







    }

}
