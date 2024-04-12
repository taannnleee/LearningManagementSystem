package com.example.learningmanagementsystem.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.learningmanagementsystem.dao.StudentDAO;
import com.example.learningmanagementsystem.models.Student;

@Database(entities = {Student.class},version = 7)
public abstract class DatabaseLearningManagerSystem extends RoomDatabase {

    private static final String DATABASE_NAME = "learning_management_system.db";
    private static DatabaseLearningManagerSystem instance;
    public static synchronized DatabaseLearningManagerSystem getInstance(Context context){
        if (instance ==null){
            instance =Room.databaseBuilder(context.getApplicationContext(), DatabaseLearningManagerSystem.class, DATABASE_NAME).allowMainThreadQueries().build();
        }
        return instance;
    }
    public abstract StudentDAO studentDAO();
}
