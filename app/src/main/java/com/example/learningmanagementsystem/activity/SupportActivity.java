package com.example.learningmanagementsystem.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.learningmanagementsystem.R;
import com.example.learningmanagementsystem.database.DatabaseLearningManagerSystem;
import com.example.learningmanagementsystem.models.Post;
import com.example.learningmanagementsystem.models.Support;
import com.example.learningmanagementsystem.models.Teacher;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SupportActivity extends AppCompatActivity {
    private EditText editTextTopic;
    private EditText editTextContent;
    private Button buttonSend, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);
        getFormWidgets();
        addEvent();

    }

    private void addEvent() {
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPost();
                String topic = editTextTopic.getText().toString();
                String content = editTextContent.getText().toString();
                if (topic.isEmpty() || content.isEmpty()) {
                    Toast.makeText(SupportActivity.this, "Vui lòng nhập đủ chủ đề và nội dung", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SupportActivity.this, "Bài viết đã được đăng", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SupportActivity.this, "Success", Toast.LENGTH_SHORT).show();
                Intent intent  = new Intent(SupportActivity.this, InteractionAdminActivity.class);
                startActivity(intent);
            }
        });

    }

    private void getFormWidgets() {
        editTextTopic = findViewById(R.id.editTextTopic);
        editTextContent = findViewById(R.id.editTextContent);
        buttonSend = findViewById(R.id.buttonSend);
        btnBack = findViewById(R.id.btnBack);
    }

    private void addPost() {
        String topic = editTextTopic.getText().toString();
        String content = editTextContent.getText().toString();

        if (!topic.isEmpty() && !content.isEmpty()) {
            Support support = setSupportData();
            DatabaseLearningManagerSystem.getInstance(this).supportDAO().insertSupport(support);
            Toast.makeText(this, "Bài viết đã được đăng", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Vui lòng nhập đủ chủ đề và nội dung", Toast.LENGTH_SHORT).show();
        }
    }
    private Support setSupportData() {
        Support newsupport = new Support();
        newsupport.setSupportTitle(editTextTopic.getText().toString());
        newsupport.setSupportContent(editTextContent.getText().toString());
        return newsupport;
    }
}