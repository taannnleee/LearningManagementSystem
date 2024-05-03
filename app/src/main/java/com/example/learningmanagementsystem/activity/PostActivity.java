package com.example.learningmanagementsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.learningmanagementsystem.R;
import com.example.learningmanagementsystem.database.DatabaseLearningManagerSystem;
import com.example.learningmanagementsystem.models.Post;

import java.util.List;

public class PostActivity extends AppCompatActivity {
    private EditText editTextTopic;
    private EditText editTextContent;
    private Button buttonSend, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
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

    private void getFormWidgets() {
        editTextTopic = findViewById(R.id.editTextTopic);
        editTextContent = findViewById(R.id.editTextContent);
        buttonSend = findViewById(R.id.buttonSend);
        btnBack = findViewById(R.id.btnBack);
    }

    private void addPost() {
        Post post = setPostData();
        DatabaseLearningManagerSystem.getInstance(this).postDAO().insertPost(post);
        List<Post> posts = DatabaseLearningManagerSystem.getInstance(this).postDAO().getAllPost();
    }
    private Post setPostData() {
        Post newpost = new Post();
        newpost.setPostTitle(editTextTopic.getText().toString());
        newpost.setPostContent(editTextContent.getText().toString());
        return newpost;
    }
}
