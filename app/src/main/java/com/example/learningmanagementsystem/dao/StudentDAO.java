package com.example.learningmanagementsystem.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.learningmanagementsystem.models.Classes;
import com.example.learningmanagementsystem.models.Student;

import java.util.List;

@Dao
public interface StudentDAO {

    @Insert
    void insertStudent(Student... student);

    @Query("SELECT COUNT(*) FROM students")
    int getStudentCount();

    @Query("select * from students")
    List<Student> getAllStudent();
    @Query("SELECT * FROM students WHERE studentEmail = :email")
    Student getStudentByEmail(String email);
    @Query("SELECT * FROM students WHERE studentId = :studentId")
    Student getStudentById(int studentId);

    @Query("SELECT * FROM students WHERE studentId = :studentId")
    List<Student> getListStudentById(int studentId);

    @Query("UPDATE students SET studentPassword = :newPassword WHERE studentId = :id")
    void updatePassword(String newPassword, int id);
}
