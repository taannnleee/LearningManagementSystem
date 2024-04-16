package com.example.learningmanagementsystem.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.learningmanagementsystem.models.Student;
import com.example.learningmanagementsystem.models.Teacher;

import java.util.List;

@Dao
public interface TeacherDAO {
    @Insert
    void insertTeacher(Teacher... teacher);

    @Query("select * from teachers")
    List<Teacher> getAllTeacher();

    @Query("SELECT * FROM teachers WHERE teacherId = :id")
    public Teacher getTeacherById(int id);
    @Query("SELECT * FROM teachers WHERE teacherEmail = :email")
    Teacher getTeacherByEmail(String email);
}
