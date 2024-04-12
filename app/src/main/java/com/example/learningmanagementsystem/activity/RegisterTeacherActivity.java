package com.example.learningmanagementsystem.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learningmanagementsystem.R;
import com.example.learningmanagementsystem.database.DatabaseLearningManagerSystem;
import com.example.learningmanagementsystem.models.Teacher;

import java.util.List;

public class RegisterTeacherActivity extends AppCompatActivity {

    private EditText edtLastName;
    private EditText edtFirstName;
    private EditText edtPhoneNumber;
    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnCreateTeacher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_teacher);

        getFormWidgets();
        addEvent();
    }

    private void addEvent() {
        btnCreateTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTeacher();
            }
        });

    }

    private void addTeacher() {
        Teacher Teacher = setTeacherData();
        DatabaseLearningManagerSystem.getInstance(this).teacherDAO().insertTeacher(Teacher);
    }

    private void getFormWidgets() {
        edtEmail = findViewById(R.id.edtemail);
        edtPassword = findViewById(R.id.edtpassword);
        edtFirstName = findViewById(R.id.edtfirstname);
        edtPhoneNumber = findViewById(R.id.edtphone);
        edtLastName = findViewById(R.id.edtlastname);

        btnCreateTeacher = findViewById(R.id.btnCreateTeacher);
    }
    private Teacher setTeacherData(){
        Teacher newTeacher = new Teacher();
        newTeacher.setTeacherEmail(edtEmail.getText().toString());
        newTeacher.setTeacherPassword(edtPassword.getText().toString());
        newTeacher.setTeacherPhone(edtPhoneNumber.getText().toString());
        newTeacher.setTeacherAddress(edtFirstName.getText().toString());
        newTeacher.setTeacherAddress(edtLastName.getText().toString());

        return newTeacher;
    }
}