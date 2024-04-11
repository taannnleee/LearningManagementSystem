package com.example.learningmanagementsystem.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.learningmanagementsystem.dao.StudentDAO;
import com.example.learningmanagementsystem.models.Student;

@androidx.room.Database(entities = {Student.class},version = 1)
public abstract class Database  extends RoomDatabase {

    private static final String DATABASE_NAME = "learning_management_system.db";
    private static Database instance;
    public static synchronized Database getInstance(Context context){
        if (instance ==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),Database.class, DATABASE_NAME).allowMainThreadQueries().build();
        }
        return instance;
    }
    public abstract StudentDAO studentDAO();
}
