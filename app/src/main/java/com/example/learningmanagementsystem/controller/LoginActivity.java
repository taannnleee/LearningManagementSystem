package com.example.learningmanagementsystem.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.learningmanagementsystem.R;
import com.example.learningmanagementsystem.models.Person;
import com.example.learningmanagementsystem.dao.StudentDAO;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        StudentDAO studentDAO = new StudentDAO(getApplicationContext());
        Person firstStudent = studentDAO.getFirstStudent();

        if (firstStudent != null) {
            System.out.println("First Student - ID: " + firstStudent.getId() + ", Name: " + firstStudent.getName());

            Toast.makeText(getApplicationContext(), "First Student - ID: " + firstStudent.getId() + ", Name: " + firstStudent.getName(), Toast.LENGTH_SHORT).show();

        } else {
            System.out.println("No students found!");
        }

    }
}