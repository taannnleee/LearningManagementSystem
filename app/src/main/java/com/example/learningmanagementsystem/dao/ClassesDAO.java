package com.example.learningmanagementsystem.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.learningmanagementsystem.models.Classes;
import com.example.learningmanagementsystem.models.ClassesWithTeacher;
import com.example.learningmanagementsystem.models.Student;

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

    @Transaction
    @Query("SELECT * FROM classes WHERE classId = :classId")
    LiveData<ClassesWithTeacher> getClassesWithTeacherById(int classId);
}
