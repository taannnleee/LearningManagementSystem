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
import com.example.learningmanagementsystem.adapter.ScheduleArrayAdapter;
import com.example.learningmanagementsystem.database.DatabaseLearningManagerSystem;
import com.example.learningmanagementsystem.models.Classes;
import com.example.learningmanagementsystem.models.Schedule;
import com.example.learningmanagementsystem.models.StudentClassCrossRef;

import java.util.ArrayList;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ArrayList<Schedule> arrSchedule = new ArrayList<Schedule>();
    ScheduleArrayAdapter adapter = null;
    ListView lvSchedule = null;
    Dialog dialog;
    Button back_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        getFormWidgets();
        addEvent();

        Intent intent  = getIntent();
        String selectedClassId = intent.getStringExtra("selectedClassId");


        arrSchedule = (ArrayList<Schedule>) DatabaseLearningManagerSystem.getInstance(this).scheduleDAO().getScheduleByClassId(selectedClassId);
        Classes classes =(Classes)DatabaseLearningManagerSystem.getInstance(this).classDAO().getClassesById(Integer.parseInt(selectedClassId));

        adapter = new ScheduleArrayAdapter(ScheduleActivity.this, R.layout.item_schedule_layout,arrSchedule,classes);
        lvSchedule.setAdapter(adapter);
    }
    private void addEvent() {

        lvSchedule.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Classes selectedClass = arrClasses.get(position);
//                String selectedClassId = String.valueOf(selectedClass.getClassId());
//                // Tạo Intent
//                Intent intent = new Intent(MainAbsentActivity.this, ShowListStudentTeacherActivity.class);
//                intent.putExtra("selectedClassId", selectedClassId);
//                startActivity(intent);

            }
        });
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainAbsentActivity.this, NavigationBarActivity.class);
//                startActivity(intent);
            }
        });
    }
    private void getFormWidgets() {
        lvSchedule = findViewById(R.id.lvSchedule);
        back_button = findViewById(R.id.back_button);
    }
}