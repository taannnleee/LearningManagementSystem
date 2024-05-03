package com.example.learningmanagementsystem.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.learningmanagementsystem.models.Attendance;
import com.example.learningmanagementsystem.models.Schedule;

import java.util.List;

@Dao
public interface AttendanceDAO {
    @Insert
    void insertAttendance(Attendance... attendances);
    @Query("SELECT * FROM attendance WHERE id  = :id")
    Attendance getAttendanceId(int id);
}
