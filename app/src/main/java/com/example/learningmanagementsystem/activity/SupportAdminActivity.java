package com.example.learningmanagementsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.learningmanagementsystem.R;
import com.example.learningmanagementsystem.adapter.PostArrayAdapter;
import com.example.learningmanagementsystem.adapter.SupportArrayAdapter;
import com.example.learningmanagementsystem.database.DatabaseLearningManagerSystem;
import com.example.learningmanagementsystem.models.Post;
import com.example.learningmanagementsystem.models.Support;

import java.util.ArrayList;

public class SupportAdminActivity extends AppCompatActivity {
    private ListView listViewAnnouncements;
    private Button btnBack;
    private ArrayList<Support> arrSupport;
    private SupportArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_support);
        getFormWidgets();
        addEvent();

        arrSupport = new ArrayList<>();
        arrSupport.addAll(DatabaseLearningManagerSystem.getInstance(this).supportDAO().getAllSupport());

        adapter = new SupportArrayAdapter(SupportAdminActivity.this, R.layout.support_item, arrSupport);
        listViewAnnouncements.setAdapter(adapter);
    }

    private void addEvent() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SupportAdminActivity.this, InteractionAdminActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getFormWidgets() {
        listViewAnnouncements = findViewById(R.id.listViewAnnouncements);
        btnBack = findViewById(R.id.buttonBack);
    }
}
