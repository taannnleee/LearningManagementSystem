package com.example.learningmanagementsystem.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learningmanagementsystem.R;
import com.example.learningmanagementsystem.dao.StudentDAO;
import com.example.learningmanagementsystem.helper.SQLiteHelper;
import com.example.learningmanagementsystem.models.Student;

public class LoginActivity extends AppCompatActivity {
    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnLogin; // Initialize Button object
    private TextView txtviewregister;
    private RadioButton radioStudent, radioTeacher, radioAdmin;
    private String selectedRole = "";
    private StudentDAO studentDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        studentDAO = new StudentDAO(getApplicationContext());
        getFormWidgets(); // Call this method first to initialize widgets
        addEvent();
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
                    if(selectedRole =="") {
                        Toast.makeText(LoginActivity.this, "Please select role", Toast.LENGTH_SHORT).show();
                    } else if(selectedRole == "student") {
                        Boolean checkCredentials = studentDAO.checkEmailPassword(student.getEmail(), student.getPassword());
                        if(checkCredentials) {
                            Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();

                }
            }
        });
        txtviewregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
        radioStudent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    selectedRole = "student";
                    // Xử lý khi RadioButton "Student" được chọn
                }
            }
        });

        radioTeacher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    selectedRole = "teacher";

                }
            }
        });

        radioAdmin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    selectedRole = "admin";

                }
            }
        });




    }

    private void getFormWidgets() {
        edtEmail = findViewById(R.id.login_email);
        edtPassword = findViewById(R.id.login_password);
        btnLogin = findViewById(R.id.login_button);
        txtviewregister = findViewById(R.id.loginRedirectText);
        radioStudent = findViewById(R.id.radio_student);
        radioTeacher = findViewById(R.id.radio_teacher);
        radioAdmin = findViewById(R.id.radio_admin);
    }

    private Student setStudentData(){
        Student newStudent = new Student();
        newStudent.setEmail(edtEmail.getText().toString());
        newStudent.setPassword(edtPassword.getText().toString());
        return newStudent;
    }

}
