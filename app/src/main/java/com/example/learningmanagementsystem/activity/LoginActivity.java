package com.example.learningmanagementsystem.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.learningmanagementsystem.R;
import com.example.learningmanagementsystem.dao.StudentDAO;
import com.example.learningmanagementsystem.helper.SQLiteHelper;
import com.example.learningmanagementsystem.models.Student;

public class LoginActivity extends AppCompatActivity {
    private EditText edtEmail;
    private EditText edtPassword;
    private RadioGroup radioGroup;
    private Button btnLogin;
    SQLiteHelper db;



    private StudentDAO studentDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        studentDAO = new StudentDAO(getApplicationContext());
        addEvent();
        getFormWidgets();
    }
    public void addEvent() {
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Student student = setStudentData();
                if(student.getEmail().equals("") || student.getPassword().equals("")) {
                    Toast.makeText(LoginActivity.this, "Please complete all information", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean checkCredentials = studentDAO.checkEmailPassword(student.getEmail(), student.getPassword());
                    if(checkCredentials == true) {
                        Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);

                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void getFormWidgets() {
        edtEmail = findViewById(R.id.login_email);
        edtPassword = findViewById(R.id.login_password);



    }
    private Student setStudentData(){
        Student newStudent = new Student();
        newStudent.setEmail(edtEmail.getText().toString());
        newStudent.setPassword(edtPassword.getText().toString());
        return newStudent;
    }

}