package com.example.learningmanagementsystem.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Time;
import java.util.Date;

@Entity(tableName = "classes")
public class Classes {

    @PrimaryKey(autoGenerate = true)
    private int classId;

    private String className;

    private String classDescription;


    private String classInstructor;


    private String classDuration;

    private String classLevel;

    private Date startDate;
    private Date endDate;


    public Classes() {
        // Default constructor required by Room
    }

    public Classes(String className, String classDescription, String classInstructor, String classDuration, String classLevel) {
        this.className = className;
        this.classDescription = classDescription;
        this.classInstructor = classInstructor;
        this.classDuration = classDuration;
        this.classLevel = classLevel;
    }

    public int getClassId() {
        return classId;
    }

    public String getClassName() {
        return className;
    }

    public String getClassDescription() {
        return classDescription;
    }

    public String getClassInstructor() {
        return classInstructor;
    }

    public String getClassDuration() {
        return classDuration;
    }

    public String getClassLevel() {
        return classLevel;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setClassDescription(String classDescription) {
        this.classDescription = classDescription;
    }

    public void setClassInstructor(String classInstructor) {
        this.classInstructor = classInstructor;
    }

    public void setClassDuration(String classDuration) {
        this.classDuration = classDuration;
    }

    public void setClassLevel(String classLevel) {
        this.classLevel = classLevel;
    }
}