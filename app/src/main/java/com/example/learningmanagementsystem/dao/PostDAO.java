package com.example.learningmanagementsystem.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.learningmanagementsystem.models.Admin;
import com.example.learningmanagementsystem.models.Post;

import java.util.List;

@Dao
public interface PostDAO {
    @Insert
    void insertPost(Post... post);

    @Query("select * from post")
    List<Post> getAllPost();
}
