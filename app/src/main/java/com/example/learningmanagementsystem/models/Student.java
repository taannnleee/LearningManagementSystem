package com.example.learningmanagementsystem.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "students")
public class Student {

    @PrimaryKey(autoGenerate = true)
    private int studentId;
    private String studentName;
    private String studentEmail;
    private String studentPassword;
    private String studentAddress;
    private String studentStatus;
    private String studentPhone;

    public Student() {
    }

    public Student(String studentName, String studentEmail, String studentPassword, String studentAddress, String studentStatus, String studentPhone) {
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.studentPassword = studentPassword;
        this.studentAddress = studentAddress;
        this.studentStatus = studentStatus;
        this.studentPhone = studentPhone;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public String getStudentAddress() {
        return studentAddress;
    }


    public String getStudentStatus() {
        return studentStatus;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    public void setStudentStatus(String studentStatus) {
        this.studentStatus = studentStatus;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }
}
