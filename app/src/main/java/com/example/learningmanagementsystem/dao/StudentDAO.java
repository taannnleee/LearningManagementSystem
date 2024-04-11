package com.example.learningmanagementsystem.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.learningmanagementsystem.models.Student;
import com.example.learningmanagementsystem.helper.SQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private SQLiteDatabase db;

    public StudentDAO(Context context) {
        SQLiteHelper dbHelper = new SQLiteHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public void registerStudent (Student student) {

        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_STUDENT_NAME, student.getName());
        values.put(SQLiteHelper.COLUMN_STUDENT_ADDRESS, student.getAddress());
        values.put(SQLiteHelper.COLUMN_STUDENT_PHONE, student.getPhone());
        values.put(SQLiteHelper.COLUMN_STUDENT_EMAIL, student.getEmail());
        values.put(SQLiteHelper.COLUMN_STUDENT_PASSWORD, student.getPassword());
        values.put(SQLiteHelper.COLUMN_STUDENT_ROLE, student.getRole());
        values.put(SQLiteHelper.COLUMN_STUDENT_STATUS, student.getStatus());

        // Thực hiện chèn dữ liệu vào bảng sinh viên
        long newRowId = db.insert(SQLiteHelper.TABLE_STUDENTS, null, values);
        // Đóng kết nối tới cơ sở dữ liệu
        db.close();
    }

    public List<Student> getAllStudent() {
        List<Student> danhSachCongViec = new ArrayList<>();
        String selectQuery = "SELECT * FROM "+ SQLiteHelper.TABLE_STUDENTS;
        Cursor cursor = db.rawQuery (selectQuery, null);
        int a = cursor.getCount();
        if (cursor.moveToFirst()) {
            do {
                Student congViec = new Student();
                congViec.setId(cursor.getInt(0));
                congViec.setName (cursor.getString(1));
                danhSachCongViec.add(congViec);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return danhSachCongViec;
    }

//    @SuppressLint("Range")
    public Student getFirstStudent() {
        String selectQuery = "SELECT " + SQLiteHelper.COLUMN_STUDENT_NAME + " FROM " + SQLiteHelper.TABLE_STUDENTS + " LIMIT 1";
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            Student person = new Student();
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
    public Boolean checkEmail(String email) {
        String selectQuery = "SELECT * FROM "+ SQLiteHelper.TABLE_STUDENTS+" WHERE COLUMN_STUDENT_EMAIL = ?";
        Cursor cursor = db.rawQuery(selectQuery,  new String[]{email});
        int a = cursor.getCount();
        if(a > 0) {
            return true;
        }
        else return false;
    }
    public Boolean checkEmailPassword(String email, String password) {
        String selectQuery = "SELECT * FROM "+ SQLiteHelper.TABLE_STUDENTS+" WHERE COLUMN_STUDENT_EMAIL = ? AND COLUMN_STUDENT_PASSWORD = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{email, password});
        int a = cursor.getCount();

        if(a > 0) {
            return true;
        }
        else return false;
    }


}
