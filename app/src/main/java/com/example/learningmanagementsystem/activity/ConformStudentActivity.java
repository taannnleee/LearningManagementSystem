package com.example.learningmanagementsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.learningmanagementsystem.R;

import java.util.ArrayList;
import java.util.Arrays;

public class ConformStudentActivity extends AppCompatActivity {
    private ListView listViewStudents;
    private Button buttonTickAll, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conform_student);

        listViewStudents = findViewById(R.id.listViewStudents);
        buttonTickAll = findViewById(R.id.buttonTickAll);
        btnBack = findViewById(R.id.btn_back_interaction);

        // Sample data for students
        ArrayList<String> studentsList = new ArrayList<>(Arrays.asList("John Doe", "Jane Smith", "Michael Johnson"));

        // Adapter for populating data into the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.student_item, R.id.textViewName, studentsList);
        listViewStudents.setAdapter(adapter);

        buttonTickAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tickAllCheckBoxes();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConformStudentActivity.this, InteractionAdminActivity.class);
                startActivity(intent);

            }
        });
    }

    private void tickAllCheckBoxes() {
        // Iterate through each list item to find the checkbox and tick it
        for (int i = 0; i < listViewStudents.getChildCount(); i++) {
            View view = listViewStudents.getChildAt(i);
            CheckBox checkBox = view.findViewById(R.id.checkBoxConform);
            checkBox.setChecked(true);
        }
    }
}
