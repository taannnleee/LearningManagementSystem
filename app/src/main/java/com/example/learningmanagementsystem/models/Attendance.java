package com.example.learningmanagementsystem.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "attendance")
public class Attendance {
    @PrimaryKey(autoGenerate = true)
    private int id; // Trường ID riêng

    private int scheduleId;
    private int studentClassCrossRefId;

    private String status;

    public int getId() {
        return id;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public int getStudentClassCrossRefId() {
        return studentClassCrossRefId;
    }

    public String getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public void setStudentClassCrossRefId(int studentClassCrossRefId) {
        this.studentClassCrossRefId = studentClassCrossRefId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Attendance(int scheduleId, int studentClassCrossRefId, String status) {
        this.scheduleId = scheduleId;
        this.studentClassCrossRefId = studentClassCrossRefId;
        this.status = status;
    }
}

