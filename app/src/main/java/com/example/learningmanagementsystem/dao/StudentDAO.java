package com.example.learningmanagementsystem.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.learningmanagementsystem.models.Student;

import java.util.List;

@Dao
public interface StudentDAO {

    @Insert
    void insertStudent(Student... student);

    @Query("select * from students")
    List<Student> getAllStudent();
    @Query("SELECT * FROM students WHERE studentEmail = :email")
    Student getStudentByEmail(String email);
}
