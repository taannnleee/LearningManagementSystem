package com.example.learningmanagementsystem.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.learningmanagementsystem.models.Classes;

import java.util.List;

@Dao
public interface ClassesDAO {
    @Insert
    long insertNewClass(Classes classes);

    @Query("select * from classes")
    List<Classes> getAllClasses();

    @Query("SELECT * FROM classes WHERE classCourse = :classCourse")
    List<Classes> getClassesByClassCourse(String classCourse);

    @Query("SELECT * FROM classes WHERE classid = :classid")
    Classes getClassesById(int classid);

    @Query("SELECT * FROM classes WHERE teacherId = :teacherId")
    List<Classes> getClassesByTeacherId(int teacherId);



}
