package com.example.learningmanagementsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.learningmanagementsystem.R;

public class PostActivity extends AppCompatActivity {
    private EditText editTextTopic;
    private EditText editTextContent;
    private Button buttonSend, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        editTextTopic = findViewById(R.id.editTextTopic);
        editTextContent = findViewById(R.id.editTextContent);
        buttonSend = findViewById(R.id.buttonSend);
        btnBack = findViewById(R.id.btnBack);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy nội dung từ EditTexts
                String topic = editTextTopic.getText().toString();
                String content = editTextContent.getText().toString();
                if (topic.isEmpty() || content.isEmpty()) {
                    Toast.makeText(PostActivity.this, "Vui lòng nhập đủ chủ đề và nội dung", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PostActivity.this, "Bài viết đã được đăng", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PostActivity.this, "Success", Toast.LENGTH_SHORT).show();
                Intent intent  = new Intent(PostActivity.this, InteractionAdminActivity.class);
                startActivity(intent);
            }
        });
    }
}
