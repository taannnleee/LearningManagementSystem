package com.example.learningmanagementsystem.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.learningmanagementsystem.R;

public class SupportActivity extends AppCompatActivity {
    ImageButton btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);
        getFormWidgets(); // Call this method first to initialize widgets
        addEvent();

    }

    private void addEvent() {
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SupportActivity.this, InteractionAdminActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getFormWidgets() {
        btn_back = findViewById(R.id.btn_back);

    }
}