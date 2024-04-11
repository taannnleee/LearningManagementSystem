package com.example.learningmanagementsystem.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.learningmanagementsystem.models.Student;


import java.util.ArrayList;
import java.util.List;
@Dao
public interface StudentDAO {
    @Insert
    void insertStudent(Student student);

//    @Query("Select * from Student")
//    List<Student> getListStudent();



}
