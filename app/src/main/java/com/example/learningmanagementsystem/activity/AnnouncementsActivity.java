package com.example.learningmanagementsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.learningmanagementsystem.R;
import com.example.learningmanagementsystem.adapter.ClassesArrayAdapter;
import com.example.learningmanagementsystem.adapter.PostArrayAdapter;
import com.example.learningmanagementsystem.database.DatabaseLearningManagerSystem;
import com.example.learningmanagementsystem.models.Classes;
import com.example.learningmanagementsystem.models.Post;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnnouncementsActivity extends AppCompatActivity {
    private ListView listViewAnnouncements;
    private Button btnBack;
    private ArrayList<Post> arrPost;
    private PostArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcements);
        getFormWidgets();
        addEvent();
        arrPost = new ArrayList<>();
        arrPost.addAll(DatabaseLearningManagerSystem.getInstance(this).postDAO().getAllPost());
        adapter = new PostArrayAdapter(AnnouncementsActivity.this, R.layout.announcements_item, arrPost);
        listViewAnnouncements.setAdapter(adapter);
    }

    private void addEvent() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnnouncementsActivity.this, InteractionActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getFormWidgets() {
        listViewAnnouncements = findViewById(R.id.listViewAnnouncements);
        btnBack = findViewById(R.id.buttonBack);
    }

}
