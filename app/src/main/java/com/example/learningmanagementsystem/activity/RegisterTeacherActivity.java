package com.example.learningmanagementsystem.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.learningmanagementsystem.R;
import com.example.learningmanagementsystem.database.DatabaseLearningManagerSystem;
import com.example.learningmanagementsystem.models.Teacher;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;

public class RegisterTeacherActivity extends AppCompatActivity {

    private EditText edtLastName;
    private EditText edtFirstName;
    private EditText edtPhoneNumber;
    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnCreateTeacher, btnBackInteraction;

    private ImageView imgBirthday;

    private EditText edtBirthday;

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
                try {
                    addTeacher();
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        imgBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calender = Calendar.getInstance();
                int year = calender.get(Calendar.YEAR);
                int month = calender.get(Calendar.MONTH);
                int day = calender.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(RegisterTeacherActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Toast.makeText(RegisterTeacherActivity.this, String.format("select : %d/%d/%d",dayOfMonth, (month+1), year),
                                Toast.LENGTH_SHORT).show();

                        edtBirthday.setText(String.format("%d/%d/%d",dayOfMonth, (month+1), year));
                    }
                }, year ,month,day);
                dialog.show();
            }
        });
        btnBackInteraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InteractionAdminActivity.class);
                startActivity(intent);
            }
        });

    }

    private void addTeacher() throws ParseException {
        Teacher Teacher = setTeacherData();
        DatabaseLearningManagerSystem.getInstance(this).teacherDAO().insertTeacher(Teacher);
    }

    private void getFormWidgets() {
        edtEmail = findViewById(R.id.edtemail);
        edtPassword = findViewById(R.id.edtpassword);
        edtFirstName = findViewById(R.id.edtfirstName);
        edtPhoneNumber = findViewById(R.id.edtphone);
        edtLastName = findViewById(R.id.edtlastname);
        imgBirthday = findViewById(R.id.imageBirthday);
        edtBirthday = findViewById(R.id.edtBirthday11);

        btnCreateTeacher = findViewById(R.id.btnCreateTeacher);
    }
    private Teacher setTeacherData() throws ParseException {
        Teacher newTeacher = new Teacher();
        newTeacher.setTeacherEmail(edtEmail.getText().toString());
        newTeacher.setTeacherPassword(edtPassword.getText().toString());
        newTeacher.setTeacherPhone(edtPhoneNumber.getText().toString());
        newTeacher.setTeacherName(edtLastName.getText().toString()+ " " + edtFirstName.getText().toString());

        String birthday_ = edtBirthday.getText().toString();
        Date birthday = new SimpleDateFormat("dd/MM/yyyy").parse(birthday_);
        newTeacher.setBirthday(birthday);
        return newTeacher;
    }
}