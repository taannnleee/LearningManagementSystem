package com.example.learningmanagementsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.learningmanagementsystem.R;
import com.example.learningmanagementsystem.database.DatabaseLearningManagerSystem;
import com.example.learningmanagementsystem.models.Classes;
import com.example.learningmanagementsystem.models.Teacher;

import java.util.ArrayList;

public class CourseDetailsActivity extends AppCompatActivity {
    private ImageButton btn_back;
    private  ArrayList<Classes> arrClasses;
    String classCourse;
    String selectedClassId;
    Button button, btn_send;
    TextView tv_teacher_name_value, tv_price_value, tv_course_details_value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        Intent intent = getIntent();
        classCourse = intent.getStringExtra("classCourse");
        selectedClassId = intent.getStringExtra("selectedClassId");

        getFormWidgets();
        addEvent();
        loadInfor();
    }

    private void loadInfor() {
        Classes classes =  (Classes) DatabaseLearningManagerSystem.getInstance(this).classDAO().getClassesById(Integer.parseInt(selectedClassId));
        Teacher teacher =  (Teacher) DatabaseLearningManagerSystem.getInstance(this).teacherDAO().getTeacherById(classes.getTeacherId());
        tv_teacher_name_value.setText(teacher.getTeacherName()+"");

        tv_course_details_value.setText(classes.getClassDescription());
        tv_price_value.setText(classes.getClassFee()+"");
    }

    private void addEvent() {
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CourseDetailsActivity.this, ListClassesShowStudent.class);
                intent.putExtra("classCourse",classCourse);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void getFormWidgets() {
        btn_back = findViewById(R.id.btn_back);
        button = findViewById(R.id.button);
        btn_send = findViewById(R.id.btn_send);
        tv_teacher_name_value = findViewById(R.id.tv_teacher_name_value);
        tv_course_details_value = findViewById(R.id.tv_course_details_value);
        tv_price_value = findViewById(R.id.tv_price_value);
    }
}
