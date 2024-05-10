package com.example.learningmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.learningmanagementsystem.activity.MainTeacherActivity;
import com.example.learningmanagementsystem.activity.ScheduleActivity;
import com.example.learningmanagementsystem.activity.ShowListStudentTeacherActivity;
import com.example.learningmanagementsystem.adapter.ScheduleArrayAdapter;
import com.example.learningmanagementsystem.adapter.ScheduleArrayAdapterTeacher;
import com.example.learningmanagementsystem.database.DatabaseLearningManagerSystem;
import com.example.learningmanagementsystem.models.Classes;
import com.example.learningmanagementsystem.models.Schedule;

import java.util.ArrayList;

public class ShowSheduleTeacherActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ArrayList<Schedule> arrSchedule = new ArrayList<Schedule>();
    ScheduleArrayAdapterTeacher adapter = null;
    ListView lvSchedule = null;
    Dialog dialog;
    Button back_button;
    ImageButton logout_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_shedule_teacher);

        getFormWidgets();
        addEvent();

        Intent intent  = getIntent();
        String selectedClassId = intent.getStringExtra("selectedClassId");


        arrSchedule = (ArrayList<Schedule>) DatabaseLearningManagerSystem.getInstance(this).scheduleDAO().getScheduleByClassId(selectedClassId);
        Classes classes =(Classes)DatabaseLearningManagerSystem.getInstance(this).classDAO().getClassesById(Integer.parseInt(selectedClassId));

        adapter = new ScheduleArrayAdapterTeacher(ShowSheduleTeacherActivity.this, R.layout.item_schedule_layout_teacher,arrSchedule,classes);
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
                Intent intent = new Intent(ShowSheduleTeacherActivity.this, MainTeacherActivity.class);
                startActivity(intent);
                finish();
            }
        });
        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowSheduleTeacherActivity.this, MainTeacherActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void getFormWidgets() {
        lvSchedule = findViewById(R.id.lvSchedule);
        back_button = findViewById(R.id.back_button);
        logout_button = findViewById(R.id.logout_button);
    }
}