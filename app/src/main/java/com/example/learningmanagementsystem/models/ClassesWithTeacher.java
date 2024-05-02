package com.example.learningmanagementsystem.models;

import androidx.room.Embedded;
import androidx.room.Relation;

public class ClassesWithTeacher {
    @Embedded
    public Classes classes;

    @Relation(parentColumn = "teacherId", entityColumn = "teacherId")
    public Teacher teacher;
}
