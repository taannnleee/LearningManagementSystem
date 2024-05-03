package com.example.learningmanagementsystem.activity;

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

import com.example.learningmanagementsystem.R;
import com.example.learningmanagementsystem.adapter.ClassesArrayAdapterTeacher;
import com.example.learningmanagementsystem.database.DatabaseLearningManagerSystem;
import com.example.learningmanagementsystem.models.Classes;
import com.example.learningmanagementsystem.models.StudentClassCrossRef;

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

        ArrayList<StudentClassCrossRef> arrStudentClassCrossRef = new ArrayList<StudentClassCrossRef>();
        arrStudentClassCrossRef= (ArrayList<StudentClassCrossRef>) DatabaseLearningManagerSystem.getInstance(this).studentClassCrossRefDAO().getLisstudentIdByStudentIdAndStatus(Integer.parseInt(current_studentId), "active");

        for (int i = 0; i < arrStudentClassCrossRef.size(); i++) {
            StudentClassCrossRef studentClassCrossRef = arrStudentClassCrossRef.get(i);
            int temp = studentClassCrossRef.getCourseId();

            Classes classes = new Classes();
            classes =  (Classes) DatabaseLearningManagerSystem.getInstance(this).classDAO().getClassesById(temp);
            arrClasses.add(classes);
        }

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
                Intent intent = new Intent(MainAbsentActivity.this, ScheduleActivity.class);
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