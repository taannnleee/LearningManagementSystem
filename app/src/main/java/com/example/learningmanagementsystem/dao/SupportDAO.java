package com.example.learningmanagementsystem.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.learningmanagementsystem.models.Post;
import com.example.learningmanagementsystem.models.Support;

import java.util.List;

@Dao
public interface SupportDAO {
    @Insert
    void insertSupport(Support... support);

    @Query("select * from support")
    List<Support> getAllSupport();
}
