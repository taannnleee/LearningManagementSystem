package com.example.learningmanagementsystem.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.learningmanagementsystem.R;
import com.example.learningmanagementsystem.models.Student;

public class ProfileActivity extends AppCompatActivity {
    private TextView textViewName, textViewEmail, textViewPhone, textViewAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_account);

        textViewName = findViewById(R.id.register_name);
        textViewEmail = findViewById(R.id.register_email);
        textViewPhone = findViewById(R.id.register_phoneNumber);
        textViewAddress = findViewById(R.id.register_address);

        // Lấy dữ liệu sinh viên từ Intent
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            // Khởi tạo đối tượng sinh viên từ dữ liệu nhận được
            Student student = (Student) bundle.getSerializable("student");

            if(student != null){
                textViewName.setText(student.getStudentName());
                textViewEmail.setText(student.getStudentEmail());
                textViewPhone.setText(student.getStudentPhone());
                textViewAddress.setText(student.getStudentAddress());
            }
        }
    }
}
