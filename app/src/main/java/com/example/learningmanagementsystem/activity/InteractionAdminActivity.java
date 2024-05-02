package com.example.learningmanagementsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.learningmanagementsystem.R;

public class InteractionAdminActivity extends AppCompatActivity {
//    private ImageView imgVPosts, imgVAnnouncements, imgVSupports;
    ImageView btnclass;
    private ImageView imageViewAddTeacher, imageViewAddCourse, imageViewConfirm;
    private ImageView imageViewPosts, imageViewAnnouncements, imageViewSupports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interaction_admin);

        getFormWidgets();

        addEvent();
    }

    private void addEvent() {
        imageViewAddTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InteractionAdminActivity.this, RegisterTeacherActivity.class);
                startActivity(intent);
            }
        });
        imageViewAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InteractionAdminActivity.this, CreationClassActivity.class);
                startActivity(intent);
            }
        });
        imageViewConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InteractionAdminActivity.this, ConformStudentActivity.class);
                startActivity(intent);
            }
        });

        imageViewPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InteractionAdminActivity.this, PostActivity.class);
                startActivity(intent);
            }
        });
//        imageViewAnnouncements.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(InteractionAdminActivity.this, SupportActivity.class);
//                startActivity(intent);
//            }
//        });
        imageViewSupports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InteractionAdminActivity.this, SupportActivity.class);
                startActivity(intent);
            }
        });

    }

    private void getFormWidgets() {
//        btnclass = findViewById(R.id.imageViewClasses); coi chỉnh lại cái này nha Trung
        imageViewAddTeacher = findViewById(R.id.imageViewAddTeacher);
        imageViewAddCourse = findViewById(R.id.imageViewAddCourse);
        imageViewConfirm = findViewById(R.id.imageViewConfirm);
        imageViewSupports = findViewById(R.id.imageViewSupports);
        imageViewPosts = findViewById(R.id.imageViewPosts);
        imageViewAnnouncements = findViewById(R.id.imageViewAnnouncements);



    }
}

//    private void addEvent() {
//        imgVPosts.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), RegisterTeacherActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        imgVAnnouncements.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), RegisterTeacherActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        imgVSupports.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), RegisterTeacherActivity.class);
//                startActivity(intent);
//            }
//        });
//    }
//

