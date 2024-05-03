package com.example.learningmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.learningmanagementsystem.activity.LoginActivity;
import com.example.learningmanagementsystem.activity.MainTeacherActivity;
import com.example.learningmanagementsystem.activity.NavigationBarActivity;
import com.example.learningmanagementsystem.activity.ShowListStudentTeacherActivity;
import com.example.learningmanagementsystem.adapter.ClassesArrayAdapterTeacher;
import com.example.learningmanagementsystem.database.DatabaseLearningManagerSystem;
import com.example.learningmanagementsystem.models.Classes;

import java.util.ArrayList;

public class MainAbsentActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ArrayList<Classes> arrClasses = new ArrayList<Classes>();
    ClassesArrayAdapterTeacher adapter = null;
    ListView lvListClass = null;
    Dialog dialog;
    Button back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_absent);
        getFormWidgets();
        addEvent();


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String current_studentId = sharedPreferences.getString("current_studentId", "defaultValue");

//        DatabaseLearningManagerSystem.getInstance(this).studentClassCrossRefDAO().getStudentClassCrossRefByStudentAndCourse(current_studentId, "active");

        arrClasses = (ArrayList<Classes>) DatabaseLearningManagerSystem.getInstance(this).classDAO().getAllClasses();
        adapter = new ClassesArrayAdapterTeacher(MainAbsentActivity.this, R.layout.item_classes_layout_teacher,arrClasses);
        lvListClass.setAdapter(adapter);
    }

    private void addEvent() {

        lvListClass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Classes selectedClass = arrClasses.get(position);
                String selectedClassId = String.valueOf(selectedClass.getClassId());
                // Tạo Intent
                Intent intent = new Intent(MainAbsentActivity.this, ShowListStudentTeacherActivity.class);
                intent.putExtra("selectedClassId", selectedClassId);
                startActivity(intent);

            }
        });
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainAbsentActivity.this, NavigationBarActivity.class);
                startActivity(intent);
            }
        });
    }
    private void getFormWidgets() {
        lvListClass = findViewById(R.id.lvListClass);
        back_button = findViewById(R.id.back_button);
    }
}