package com.example.learningmanagementsystem.dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.learningmanagementsystem.models.Person;
import com.example.learningmanagementsystem.helper.StudentSQLiteHelper;

public class StudentDAO {
    private SQLiteDatabase db;

    public StudentDAO(Context context) {
        StudentSQLiteHelper dbHelper = new StudentSQLiteHelper(context);
        db = dbHelper.getWritableDatabase();
    }

//    @SuppressLint("Range")
    public Person getFirstStudent() {
        String selectQuery = "SELECT " + StudentSQLiteHelper.COLUMN_STUDENT_NAME + " FROM " + StudentSQLiteHelper.TABLE_STUDENTS + " LIMIT 1";
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            Person person = new Person();
            person.setName(cursor.getString(1));
            person.setId(cursor.getInt(0));

//            person.setId(cursor.getInt(cursor.getColumnIndex(StudentSQLiteHelper.COLUMN_STUDENT_ID)));
//            person.setName(cursor.getString(cursor.getColumnIndex(StudentSQLiteHelper.COLUMN_STUDENT_NAME)));

            cursor.close();
            db.close();
            return person;
        } else{
            return null;
        }


    }
}
