package com.example.learningmanagementsystem.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.learningmanagementsystem.R;
import com.example.learningmanagementsystem.adapter.ClassesArrayAdapterTeacher;
import com.example.learningmanagementsystem.adapter.StudentArrayShowTeacherAdapter;
import com.example.learningmanagementsystem.database.DatabaseLearningManagerSystem;
import com.example.learningmanagementsystem.models.Classes;
import com.example.learningmanagementsystem.models.Student;

import java.util.ArrayList;

public class ShowListStudentTeacherActivity extends AppCompatActivity {

    ArrayList<Student> arrStudent = new ArrayList<Student>();
    StudentArrayShowTeacherAdapter adapter = null;
    ListView lvListStudent = null;
    Dialog dialog;
    Button back_button;
    String classId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list_student_teacher);

        getFormWidgets();
        addEvent();

        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("selectedClassId")) {
                classId = intent.getStringExtra("selectedClassId");
            }
        }


        arrStudent = (ArrayList<Student>) DatabaseLearningManagerSystem.getInstance(this).studentClassCrossRefDAO().getListStudentByClassIdAndStatus(Integer.parseInt(classId), "inactive");
        adapter = new StudentArrayShowTeacherAdapter(ShowListStudentTeacherActivity.this, R.layout.student_show_teacher_item,arrStudent);
        lvListStudent.setAdapter(adapter);

    }
    private void addEvent() {

        lvListStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Classes selectedClass = arrClasses.get(position);
//                int selectedClassId = selectedClass.getClassId();
                // Tạo Intent
//                Intent intent = new Intent(MainTeacherActivity.this, ShowListStudentTeacherActivity.class);
//                startActivity(intent);
            }
        });
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowListStudentTeacherActivity.this, MainTeacherActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void getFormWidgets() {
        lvListStudent = findViewById(R.id.lvListStudent);
        back_button = findViewById(R.id.back_button);
    }
}