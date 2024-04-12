package com.example.learningmanagementsystem.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "classs")
public class Class {

    @PrimaryKey(autoGenerate = true)
    private int classId;

    private String className;

    private String classDescription;


    private String classInstructor;


    private String classDuration;

    private String classLevel;


    public Class() {
        // Default constructor required by Room
    }

    public Class(String className, String classDescription, String classInstructor, String classDuration, String classLevel) {
        this.className = className;
        this.classDescription = classDescription;
        this.classInstructor = classInstructor;
        this.classDuration = classDuration;
        this.classLevel = classLevel;
    }

    public int getclassId() {
        return classId;
    }

    public String getclassName() {
        return className;
    }

    public String getclassDescription() {
        return classDescription;
    }

    public String getclassInstructor() {
        return classInstructor;
    }

    public String getclassDuration() {
        return classDuration;
    }

    public String getclassLevel() {
        return classLevel;
    }

    public void setclassId(int classId) {
        this.classId = classId;
    }

    public void setclassName(String className) {
        this.className = className;
    }

    public void setclassDescription(String classDescription) {
        this.classDescription = classDescription;
    }

    public void setclassInstructor(String classInstructor) {
        this.classInstructor = classInstructor;
    }

    public void setclassDuration(String classDuration) {
        this.classDuration = classDuration;
    }

    public void setclassLevel(String classLevel) {
        this.classLevel = classLevel;
    }
}