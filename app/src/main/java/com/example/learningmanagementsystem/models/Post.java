package com.example.learningmanagementsystem.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "post")
public class Post {
    @PrimaryKey(autoGenerate = true)
    private int postId;
    private String postTitle;

    private String postContent;

    public Post(String postTitle, String postContent) {
        this.postTitle = postTitle;
        this.postContent = postContent;
    }

    public Post() {
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }
}
