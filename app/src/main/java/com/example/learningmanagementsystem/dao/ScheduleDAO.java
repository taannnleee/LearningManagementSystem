package com.example.learningmanagementsystem.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.learningmanagementsystem.models.Schedule;
import com.example.learningmanagementsystem.models.Student;
import com.example.learningmanagementsystem.models.Teacher;

import java.util.List;

@Dao
public interface ScheduleDAO {
    @Insert
    void insertSchedule(Schedule... schedules);
    @Query("SELECT * FROM schedule WHERE classId = :classId")
    List<Schedule> getScheduleByClassId(String classId);
}
