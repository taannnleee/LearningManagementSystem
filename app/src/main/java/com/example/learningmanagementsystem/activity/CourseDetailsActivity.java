package com.example.learningmanagementsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.learningmanagementsystem.R;
import com.example.learningmanagementsystem.models.Classes;

import java.util.ArrayList;

public class CourseDetailsActivity extends AppCompatActivity {
    private ImageButton btn_back;
    private  ArrayList<Classes> arrClasses;
    String classCourse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);


        Intent intent = getIntent();
        arrClasses = getIntent().getParcelableArrayListExtra("arrClasses");

        classCourse = intent.getStringExtra("classCourse");


//        String classCourse = intent.getStringExtra("classCourse");
//
//        Toast.makeText(CourseDetailsActivity.this, String.valueOf(selectedClassId), Toast.LENGTH_SHORT).show();


        getFormWidgets();
        addEvent();
    }

    private void addEvent() {
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CourseDetailsActivity.this, ListClassesShowStudent.class);
//                intent.putExtra("arrClasses", arrClasses);
                intent.putExtra("classCourse",classCourse);
                startActivity(intent);

            }
        });
    }

    private void getFormWidgets() {
        btn_back = findViewById(R.id.btn_back);
    }
}
