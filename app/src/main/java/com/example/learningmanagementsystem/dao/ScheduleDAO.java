package com.example.learningmanagementsystem.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.learningmanagementsystem.models.Schedule;
import com.example.learningmanagementsystem.models.Student;

@Dao
public interface ScheduleDAO {
    @Insert
    void insertSchedule(Schedule... schedules);
}
