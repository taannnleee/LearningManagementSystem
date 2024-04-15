package com.example.learningmanagementsystem.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.learningmanagementsystem.database.DateTypeConverter;

import java.util.Date;

@Entity (tableName = "teachers")
public class Teacher {

    @PrimaryKey(autoGenerate = true)
    private int teacherId;
    private String teacherName;
    private String teacherEmail;
    private String teacherPassword;
    private String teacherAddress;
    private String teacherPhone;
    @TypeConverters({DateTypeConverter.class})
    private Date birthday;


    public Teacher() {
    }

    public Teacher(String teacherName, String teacherEmail, String teacherPassword, String teacherAddress, String teacherPhone, Date birthday) {
        this.teacherName = teacherName;
        this.teacherEmail = teacherEmail;
        this.teacherPassword = teacherPassword;
        this.teacherAddress = teacherAddress;
        this.teacherPhone = teacherPhone;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return teacherId + " + " + teacherAddress;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public String getTeacherPassword() {
        return teacherPassword;
    }

    public String getTeacherAddress() {
        return teacherAddress;
    }

    public String getTeacherPhone() {
        return teacherPhone;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public void setTeacherPassword(String teacherPassword) {
        this.teacherPassword = teacherPassword;
    }

    public void setTeacherAddress(String teacherAddress) {
        this.teacherAddress = teacherAddress;
    }

    public void setTeacherPhone(String teacherPhone) {
        this.teacherPhone = teacherPhone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
