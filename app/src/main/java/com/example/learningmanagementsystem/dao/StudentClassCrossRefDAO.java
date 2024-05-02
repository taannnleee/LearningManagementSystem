package com.example.learningmanagementsystem.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.learningmanagementsystem.adapter.StudentItemAdapter;
import com.example.learningmanagementsystem.models.Schedule;
import com.example.learningmanagementsystem.models.StudentClassCrossRef;

import java.util.List;

@Dao
public interface StudentClassCrossRefDAO {
    @Insert
    void insertStudentClassCrossRef(StudentClassCrossRef... studentClassCrossRefs);

    @Query("SELECT * FROM student_class_crossref WHERE studentId = :studentId AND courseId = :classId")
    StudentClassCrossRef getStudentClassCrossRefByStudentAndCourse(int studentId, int classId);

    @Query("SELECT * FROM student_class_crossref WHERE status = 'inactive'")
    List<StudentClassCrossRef> getInactiveStudents();

    @Query("SELECT * FROM student_class_crossref WHERE id = :id")
    StudentClassCrossRef getById(int id);

    @Update
    void update(StudentClassCrossRef... studentClassCrossRef);
}
