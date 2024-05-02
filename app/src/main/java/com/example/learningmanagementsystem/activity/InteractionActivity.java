package com.example.learningmanagementsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.learningmanagementsystem.R;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;

public class InteractionActivity extends AppCompatActivity {
    private ImageView imgVToeic, imgVIelts, imgVCommunication;
    private ImageView imgVAssignments, imgVHomework, imgVAbsence;
    private  ImageView imgVPosts, imgVAnnouncements, imgVSupports;
    private BottomNavigationItemView action_home;
    private BottomNavigationItemView  interaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interaction);
        getFormWidgets(); // Call this method first to initialize widgets
        addEvent();
    }

    private void addEvent() {
        // cac ham chuyen trang
        interaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        action_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InteractionActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });
        imgVToeic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(InteractionActivity.this, "Success", Toast.LENGTH_SHORT).show();
                Intent  intent  = new Intent(InteractionActivity.this, ListClassesShowStudent.class);
                intent.putExtra("classCourse", "TOEIC");
                startActivity(intent);
            }
        });
        imgVIelts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(InteractionActivity.this, "Success", Toast.LENGTH_SHORT).show();
                Intent  intent  = new Intent(InteractionActivity.this, ListClassesShowStudent.class);
                intent.putExtra("classCourse", "IELTS");
                startActivity(intent);
            }
        });
        imgVCommunication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(InteractionActivity.this, "Success", Toast.LENGTH_SHORT).show();
                Intent  intent  = new Intent(InteractionActivity.this, ListClassesShowStudent.class);
                intent.putExtra("classCourse", "Giao tiếp cơ bản");
                startActivity(intent);
            }
        });
    }

    private void getFormWidgets() {
        imgVToeic = findViewById(R.id.imageViewToeic);
        imgVIelts = findViewById(R.id.imageViewIelts);
        imgVCommunication = findViewById(R.id.imageViewCommunication);
        action_home = findViewById(R.id.action_home);
        interaction = findViewById(R.id.interaction);



        //mấy cái dưới chưa dùng đến nha Trung nhớ xóa
        imgVAssignments = findViewById(R.id.imageViewAssignments);
        imgVHomework = findViewById(R.id.imageViewHomework);
        imgVAbsence = findViewById(R.id.imageViewAbsence);
        imgVPosts = findViewById(R.id.imageViewPosts);
        imgVAnnouncements = findViewById(R.id.imageViewAnnouncements);
        imgVSupports = findViewById(R.id.imageViewSupports);

    }
}
