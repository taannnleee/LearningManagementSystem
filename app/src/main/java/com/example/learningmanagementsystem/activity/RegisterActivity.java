package com.example.learningmanagementsystem.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.learningmanagementsystem.R;
import com.example.learningmanagementsystem.dao.StudentDAO;
import com.example.learningmanagementsystem.database.Database;
import com.example.learningmanagementsystem.models.Student;

public class RegisterActivity extends AppCompatActivity {
    private EditText edtEmail;
    private EditText edtPassword;
    private EditText edtName;
    private EditText edtPhoneNumber;
    private EditText edtAddress;
    private Button btnRegister;
    private TextView txtviewLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getFormWidgets();
        addEvent();

//        List<Student> students = studentDAO.getAllStudent();
//
//        for(Student student : students){
//            Toast.makeText(this, student.getName(), Toast.LENGTH_SHORT).show();
//        }
    }

    private void addEvent() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStudent();
            }
        });

        //Ham quay lai login
        txtviewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addStudent() {
        Student student = setStudentData();
        Database.getInstance(this).studentDAO().insertStudent(student);
    }

    private void getFormWidgets() {
        edtEmail = findViewById(R.id.register_email);
        edtPassword = findViewById(R.id.register_password);
        edtName = findViewById(R.id.register_name);
        edtPhoneNumber = findViewById(R.id.register_phoneNumber);
        edtAddress = findViewById(R.id.register_address);
        btnRegister = findViewById(R.id.register_button);

        //Lam nut quay lai dang nhap
        txtviewLogin = findViewById(R.id.loginRedirectText);


    }
    private Student setStudentData(){
        Student newStudent = new Student();
        newStudent.setEmail(edtEmail.getText().toString());
        newStudent.setPassword(edtPassword.getText().toString());
        newStudent.setName(edtName.getText().toString());
        newStudent.setPhone(edtPhoneNumber.getText().toString());
        newStudent.setAddress(edtAddress.getText().toString());
        newStudent.setRole("student");
        //active,inactive, lock, pendingConfirmation,
        newStudent.setStatus("active");

        return newStudent;
    }


}