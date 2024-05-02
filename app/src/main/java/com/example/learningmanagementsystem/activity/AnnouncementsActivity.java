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

import java.util.ArrayList;
import java.util.Arrays;

public class AnnouncementsActivity extends AppCompatActivity {
    private ListView listViewAnnouncements;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcements);

        listViewAnnouncements = findViewById(R.id.listViewAnnouncements);
        btnBack = findViewById(R.id.buttonBack);

        ArrayList<String> studentsList = new ArrayList<>(Arrays.asList("Điều chỉnh thời gian", "Thông báo quan trọng", "Các lớp lưu ý"));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.announcements_item, R.id.textViewTitle, studentsList);
        listViewAnnouncements.setAdapter(adapter);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnnouncementsActivity.this, InteractionAdminActivity.class);
                startActivity(intent);

            }
        });
    }

}
