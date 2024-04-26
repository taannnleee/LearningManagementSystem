package com.example.learningmanagementsystem.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.learningmanagementsystem.database.DateTypeConverter;

import java.util.Date;

@Entity(tableName = "classes", foreignKeys = @ForeignKey(entity = Teacher.class, parentColumns = "teacherId", childColumns = "teacherId"))
public class Classes {
    @PrimaryKey(autoGenerate = true)
    private int classId;
    private byte[] classPicture;
    private String className;
    private String classCourse; //loại khoá học
    private int classSize;
    private int teacherId; // Khóa ngoại đến giáo viên
    @TypeConverters({DateTypeConverter.class})
    private Date courseStart; //ngày khai giảng
    @TypeConverters({DateTypeConverter.class})
    private Date courseEnd; //ngày bế giảng
    private String studyingDates; //học vào các ngày thứ mấy
    @TypeConverters({DateTypeConverter.class})
    private Date classStart; //thời gian bắt đầu lớp học
    @TypeConverters({DateTypeConverter.class})
    private Date classEnd; //thời gian kết thúc lớp học
    private String classDescription;
    private long classFee;

    public Classes() {
        // Default constructor required by Room
    }

    public Classes(byte[] classPicture, String className, String classCourse, int classSize, int teacherId, Date courseStart, Date courseEnd, String studyingDates, Date classStart, Date classEnd, long classFee, String classDescription) {
        this.classPicture = classPicture;
        this.className = className;
        this.classCourse = classCourse;
        this.classSize = classSize;
        this.teacherId = teacherId;
        this.courseStart = courseStart;
        this.courseEnd = courseEnd;
        this.studyingDates = studyingDates;
        this.classStart = classStart;
        this.classEnd = classEnd;
        this.classFee = classFee;
        this.classDescription = classDescription;
    }

    public int getClassId() {
        return classId;
    }

    public byte[] getClassPicture() {
        return classPicture;
    }

    public void setClassPicture(byte[] classPicture) {
        this.classPicture = classPicture;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassCourse() {
        return classCourse;
    }

    public void setClassCourse(String classCourse) {
        this.classCourse = classCourse;
    }

    public int getClassSize() {
        return classSize;
    }

    public void setClassSize(int classSize) {
        this.classSize = classSize;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public Date getCourseStart() {
        return courseStart;
    }

    public void setCourseStart(Date courseStart) {
        this.courseStart = courseStart;
    }

    public Date getCourseEnd() {
        return courseEnd;
    }

    public void setCourseEnd(Date courseEnd) {
        this.courseEnd = courseEnd;
    }

    public String getStudyingDates() {
        return studyingDates;
    }

    public void setStudyingDates(String studyingDates) {
        this.studyingDates = studyingDates;
    }

    public Date getClassStart() {
        return classStart;
    }

    public void setClassStart(Date classStart) {
        this.classStart = classStart;
    }

    public Date getClassEnd() {
        return classEnd;
    }

    public void setClassEnd(Date classEnd) {
        this.classEnd = classEnd;
    }

    public String getClassDescription() {
        return classDescription;
    }

    public void setClassDescription(String classDescription) {
        this.classDescription = classDescription;
    }

    public long getClassFee() {
        return classFee;
    }

    public void setClassFee(long classFee) {
        this.classFee = classFee;
    }
}