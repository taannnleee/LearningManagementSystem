package com.example.learningmanagementsystem.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learningmanagementsystem.R;
import com.example.learningmanagementsystem.database.DatabaseLearningManagerSystem;
import com.example.learningmanagementsystem.models.Student;

import java.util.List;

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
//                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                startActivity(intent);
            }
        });

        //Ham quay lai login
        txtviewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void addStudent() {
        if (isFieldsNotEmpty()) {
            String email = edtEmail.getText().toString();
            if (isEmailAlreadyExists(email)) {
                Toast.makeText(this, "Email already exists!", Toast.LENGTH_SHORT).show();
            } else {
                Student student = setStudentData();
                DatabaseLearningManagerSystem.getInstance(this).studentDAO().insertStudent(student);
                Toast.makeText(this, "Student registered successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        } else {
            Toast.makeText(this, "Please fill in all fields!", Toast.LENGTH_SHORT).show();
        }
    }
    private boolean isEmailAlreadyExists(String email) {
        Student existingStudent = DatabaseLearningManagerSystem.getInstance(this).studentDAO().getStudentByEmail(email);
        return existingStudent != null;
    }

    private boolean isFieldsNotEmpty() {
        return !edtEmail.getText().toString().isEmpty() &&
                !edtPassword.getText().toString().isEmpty() &&
                !edtName.getText().toString().isEmpty() &&
                !edtPhoneNumber.getText().toString().isEmpty() &&
                !edtAddress.getText().toString().isEmpty();
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
        newStudent.setStudentEmail(edtEmail.getText().toString());
        newStudent.setStudentPassword(edtPassword.getText().toString());
        newStudent.setStudentName(edtName.getText().toString());
        newStudent.setStudentPhone(edtPhoneNumber.getText().toString());
        newStudent.setStudentAddress(edtAddress.getText().toString());
        //active,inactive, lock, pendingConfirmation,
        newStudent.setStudentStatus("active");

        return newStudent;
    }


}