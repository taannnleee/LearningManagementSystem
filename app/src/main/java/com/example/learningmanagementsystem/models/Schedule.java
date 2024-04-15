package com.example.learningmanagementsystem.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.sql.Time;

@Entity(tableName = "schedule", foreignKeys = @ForeignKey(entity = Classes.class, parentColumns = "classId", childColumns = "classId"))
public class Schedule {
    @PrimaryKey(autoGenerate = true)
    private int scheduleId;
    private Time startTime;
    private Time endTime;

    private int classId; // khoa ngoai

    public Schedule(){}
    public Schedule(Time startTime, Time endTime, int classId){
        this.startTime = startTime;
        this.endTime = endTime;
        this.classId = classId;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public int getClassId() {
        return classId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }
}
