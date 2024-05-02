package com.example.learningmanagementsystem.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.learningmanagementsystem.models.Schedule;
import com.example.learningmanagementsystem.models.StudentClassCrossRef;

@Dao
public interface StudentClassCrossRefDAO {
    @Insert
    void insertStudentClassCrossRef(StudentClassCrossRef... studentClassCrossRefs);
}
