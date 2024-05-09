package com.example.learningmanagementsystem.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.learningmanagementsystem.R;
import com.example.learningmanagementsystem.database.DatabaseLearningManagerSystem;
import com.example.learningmanagementsystem.models.Student;

public class PasswordChangeActivity extends AppCompatActivity {

    Student student;
    EditText edt_oldPassword, edt_newPassword, edt_retypeNewPassword;
    Button btn_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_password_change);
        getFormWidgets();
        setStudent();
        addEvent();

    }

    public void setStudent() {
        SharedPreferences shared = PreferenceManager.getDefaultSharedPreferences(this);
        String id_string = shared.getString("current_studentId", "defaultValue");
        student = DatabaseLearningManagerSystem.getInstance(this).studentDAO().getStudentById(Integer.parseInt(id_string));
        edt_oldPassword.setText(student.getStudentPassword());
    }

    public void getFormWidgets() {
        edt_newPassword = findViewById(R.id.edt_new_password);
        edt_oldPassword = findViewById(R.id.edt_old_password);
        edt_retypeNewPassword = findViewById(R.id.edt_retype_new_password);
        btn_confirm = findViewById(R.id.btn_confirm);
    }

    public void addEvent() {
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkTheSameNewPasswordInput()) {
                    DatabaseLearningManagerSystem.getInstance(getApplicationContext()).studentDAO().updatePassword(edt_newPassword.getText().toString(), student.getStudentId());
                    Toast.makeText(getApplicationContext(), "Changed successfully", Toast.LENGTH_SHORT).show();
                    Intent  intent  = new Intent(getApplicationContext(), AccountActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Can not change password. Please check the input", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean checkTheSameNewPasswordInput() {
        String newPassword = edt_newPassword.getText().toString();
        String retypeNewPassword = edt_retypeNewPassword.getText().toString();
        if (newPassword.equals(retypeNewPassword)) {
            return true;
        } else {
            return false;
        }
    }
}