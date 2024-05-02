package com.example.learningmanagementsystem.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.learningmanagementsystem.R;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationItemView action_home;
    private BottomNavigationItemView interaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFormWidgets(); // Call this method first to initialize widgets
        addEvent();
    }

    private void addEvent() {
        interaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InteractionActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getFormWidgets() {
        action_home = findViewById(R.id.action_home);
        interaction = findViewById(R.id.interaction);

    }
}