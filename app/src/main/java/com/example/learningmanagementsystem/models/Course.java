package com.example.learningmanagementsystem.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "courses")
public class Course {

    @PrimaryKey(autoGenerate = true)
    private int courseId;

    private String courseName;

    private String courseDescription;


    private String courseInstructor;


    private String courseDuration;

    private String courseLevel;


    public Course() {
        // Default constructor required by Room
    }

    public Course(String courseName, String courseDescription, String courseInstructor, String courseDuration, String courseLevel) {
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.courseInstructor = courseInstructor;
        this.courseDuration = courseDuration;
        this.courseLevel = courseLevel;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public String getCourseInstructor() {
        return courseInstructor;
    }

    public String getCourseDuration() {
        return courseDuration;
    }

    public String getCourseLevel() {
        return courseLevel;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public void setCourseInstructor(String courseInstructor) {
        this.courseInstructor = courseInstructor;
    }

    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
    }

    public void setCourseLevel(String courseLevel) {
        this.courseLevel = courseLevel;
    }
}