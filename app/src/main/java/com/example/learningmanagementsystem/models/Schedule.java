package com.example.learningmanagementsystem.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.learningmanagementsystem.database.DateTypeConverter;
import com.example.learningmanagementsystem.database.TimeTypeConverter;

import java.sql.Time;
import java.util.Date;

@Entity(tableName = "schedule", foreignKeys = @ForeignKey(entity = Classes.class, parentColumns = "classId", childColumns = "classId"))
public class Schedule {
    @PrimaryKey(autoGenerate = true)
    private int scheduleId;
    @TypeConverters({DateTypeConverter.class})
    private Date specificDate;

    private String classScheduleCode;
    private int classId; // khoa ngoai đến bảng lớp học

    public int getScheduleId() {
        return scheduleId;
    }

    public Date getSpecificDate() {
        return specificDate;
    }

    public String getClassScheduleCode() {
        return classScheduleCode;
    }

    public int getClassId() {
        return classId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public void setSpecificDate(Date specificDate) {
        this.specificDate = specificDate;
    }

    public void setClassScheduleCode(String classScheduleCode) {
        this.classScheduleCode = classScheduleCode;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }
}
