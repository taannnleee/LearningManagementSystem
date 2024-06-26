package com.example.learningmanagementsystem.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.learningmanagementsystem.R;
import com.google.android.material.textfield.TextInputEditText;

public class AbsenceRequestActivity extends AppCompatActivity {
    TextInputEditText editTextAbsenceReason, editTextAddress, editTextStartAbsenceDate,
            editTextEndAbsenceDate;
    ImageView btnBack;
    TextView btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_absence_request);

        editTextAbsenceReason = (TextInputEditText) findViewById(R.id.editAbsenceReason);
        editTextAddress = (TextInputEditText) findViewById(R.id.editAddress);
        editTextStartAbsenceDate = (TextInputEditText) findViewById(R.id.editStartAbsenceDate);
        editTextEndAbsenceDate = (TextInputEditText) findViewById(R.id.editAbsenceReason);

        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnSend = (TextView) findViewById(R.id.btnSend);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}