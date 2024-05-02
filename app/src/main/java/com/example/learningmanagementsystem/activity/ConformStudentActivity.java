package com.example.learningmanagementsystem.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.learningmanagementsystem.R;
import com.example.learningmanagementsystem.adapter.StudentItemAdapter;
import com.example.learningmanagementsystem.database.DatabaseLearningManagerSystem;
import com.example.learningmanagementsystem.models.StudentClassCrossRef;
import java.util.List;

public class ConformStudentActivity extends AppCompatActivity {
    private ListView listViewStudents;
    private Button buttonTickAll, buttonConfirm;

    List<StudentClassCrossRef> studentClassCrossRefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conform_student);

        listViewStudents = findViewById(R.id.listViewStudents);
        buttonTickAll = findViewById(R.id.buttonTickAll);
        buttonConfirm = findViewById(R.id.buttonConfirm);

        //get data from db and set to list
        uploadStudentsListWithInactiveStatus();

        // Adapter for populating data into the ListView
        //ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.student_item, R.id.textViewName, studentsList);
        //listViewStudents.setAdapter(adapter);

        buttonTickAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tickAllCheckBoxes();
            }
        });

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmStudents();
                uploadStudentsListWithInactiveStatus();
            }
        });
    }

    private void tickAllCheckBoxes() {
        // Iterate through each list item to find the checkbox and tick it
        for (int i = 0; i < listViewStudents.getChildCount(); i++) {
            View view = listViewStudents.getChildAt(i);
            CheckBox checkBox = view.findViewById(R.id.checkBoxConfirm);
            checkBox.setChecked(true);
        }
        Toast.makeText(this, "All checkboxes ticked!", Toast.LENGTH_SHORT).show();
    }

    public void getData() {
        studentClassCrossRefs = DatabaseLearningManagerSystem.getInstance(this).studentClassCrossRefDAO().getInactiveStudents();
    }

    public void confirmStudents() {
        for (int i = 0; i < listViewStudents.getChildCount(); i++) {
            View view = listViewStudents.getChildAt(i);
            CheckBox checkBox = view.findViewById(R.id.checkBoxConfirm);
            if (checkBox.isChecked()) {
                TextView textViewId = view.findViewById(R.id.tv_Id);
                int tempId = Integer.valueOf(textViewId.getText().toString());
                StudentClassCrossRef studentClassCrossRef = DatabaseLearningManagerSystem.getInstance(this).studentClassCrossRefDAO().getById(tempId);
                studentClassCrossRef.setStatus("active");
                DatabaseLearningManagerSystem.getInstance(this).studentClassCrossRefDAO().update(studentClassCrossRef);
            }
        }
        Toast.makeText(this, "Confirmed", Toast.LENGTH_SHORT).show();
    }

    public void uploadStudentsListWithInactiveStatus() {
        getData();
        StudentItemAdapter studentItemAdapter = new StudentItemAdapter(studentClassCrossRefs);
        listViewStudents.setAdapter(studentItemAdapter);
    }
}
