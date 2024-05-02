package com.example.learningmanagementsystem.activity;

import android.app.Application;

import com.example.learningmanagementsystem.dao.AdminDAO;
import com.example.learningmanagementsystem.database.DatabaseLearningManagerSystem;
import com.example.learningmanagementsystem.models.Admin;
import com.example.learningmanagementsystem.models.Student;

public class CreateAdmin  extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Đếm số lượng dòng trong bảng admin
        int adminCount = DatabaseLearningManagerSystem.getInstance(this).adminDAO().getAdminCount();
        int studentCount = DatabaseLearningManagerSystem.getInstance(this).studentDAO().getStudentCount();
        if (adminCount > 0  && studentCount >0) {
            return;
        }
        Admin admin = new Admin( "letan@gmail.com", "123123");
        DatabaseLearningManagerSystem.getInstance(this).adminDAO().insertAdmin(admin);

        Student student = new Student("letan@1", "123");
        DatabaseLearningManagerSystem.getInstance(this).studentDAO().insertStudent(student);
    }
}
