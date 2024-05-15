package com.example.learningmanagementsystem.activity;

import static java.security.AccessController.getContext;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.learningmanagementsystem.R;
import com.example.learningmanagementsystem.database.DatabaseLearningManagerSystem;
import com.example.learningmanagementsystem.models.Student;

public class ProfileActivity extends AppCompatActivity {
    private TextView tv_email, tv_password, tv_name, tv_phoneNumber, tv_address;
    private Button btn_back;
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_account);
        getFormWidgets();
        setStudent();
        addEvents();
    }

    public void setStudent() {
        SharedPreferences shared = PreferenceManager.getDefaultSharedPreferences(this);
        String id_string = shared.getString("current_studentId", "defaultValue");
        student = DatabaseLearningManagerSystem.getInstance(this).studentDAO().getStudentById(Integer.parseInt(id_string));

        tv_name.setText(student.getStudentName());
        tv_email.setText(student.getStudentEmail());
        tv_address.setText(student.getStudentAddress());
        tv_password.setText(student.getStudentPassword());
        tv_phoneNumber.setText(student.getStudentPhone());

    }

    public void getFormWidgets() {
        tv_name = findViewById(R.id.tv_name);
        tv_email = findViewById(R.id.tv_email);
        tv_address = findViewById(R.id.tv_profile_address);
        tv_password = findViewById(R.id.tv_password);
        tv_phoneNumber = findViewById(R.id.tv_phoneNumer);

        btn_back = findViewById(R.id.btn_profile_activity_back);
    }

    public void addEvents() {
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NavigationBarActivity.class);
                startActivity(intent);
            }
        });
    }
}
