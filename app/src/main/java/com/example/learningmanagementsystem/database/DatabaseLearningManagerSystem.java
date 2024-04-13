package com.example.learningmanagementsystem.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.learningmanagementsystem.dao.AdminDAO;
import com.example.learningmanagementsystem.dao.ClassesDAO;
import com.example.learningmanagementsystem.dao.StudentDAO;
import com.example.learningmanagementsystem.dao.TeacherDAO;
import com.example.learningmanagementsystem.models.Admin;
import com.example.learningmanagementsystem.models.Classes;
import com.example.learningmanagementsystem.models.Student;
import com.example.learningmanagementsystem.models.Teacher;

@Database(entities = {Student.class, Teacher.class, Classes.class, Admin.class}, version = 33)
public abstract class DatabaseLearningManagerSystem extends RoomDatabase {

    private static final String DATABASE_NAME = "learning_management_system.db";
    private static DatabaseLearningManagerSystem instance;
    public static synchronized DatabaseLearningManagerSystem getInstance(Context context){
        if (instance ==null){
            instance =Room.databaseBuilder(context.getApplicationContext(), DatabaseLearningManagerSystem.class, DATABASE_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return instance;
    }
    public abstract StudentDAO studentDAO();
    public abstract TeacherDAO teacherDAO();
    public abstract ClassesDAO classDAO();
    public abstract AdminDAO adminDAO();
}
