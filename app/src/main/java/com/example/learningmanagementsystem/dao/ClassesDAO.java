package com.example.learningmanagementsystem.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.learningmanagementsystem.models.Classes;
import com.example.learningmanagementsystem.models.Student;

import java.util.List;

@Dao
public interface ClassesDAO {
    @Insert
    long insertNewClass(Classes classes);

    @Query("select * from classes")
    List<Classes> getAllClasses();
}
