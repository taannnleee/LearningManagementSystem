package com.example.learningmanagementsystem.activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.learningmanagementsystem.R;
import com.example.learningmanagementsystem.database.DatabaseLearningManagerSystem;
import com.example.learningmanagementsystem.models.Classes;
import com.example.learningmanagementsystem.models.Student;
import com.example.learningmanagementsystem.models.StudentClassCrossRef;
import com.example.learningmanagementsystem.models.Teacher;

import java.text.ParseException;
import java.util.ArrayList;

public class CourseDetailsActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private ImageButton btn_back;
    private  ArrayList<Classes> arrClasses;
    String classCourse;
    String selectedClassId;
    Button button, btn_send, btn_OK, btnSure, btnCancel;
    TextView tv_teacher_name_value, tv_price_value, tv_course_details_value;

    Dialog dialog_buyCourse, dialogSure;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        Intent intent = getIntent();
        classCourse = intent.getStringExtra("classCourse");
        selectedClassId = intent.getStringExtra("selectedClassId");

        getFormWidgets();
        addEvent();
        loadInfor();
    }

    private void loadInfor() {
        Classes classes =  (Classes) DatabaseLearningManagerSystem.getInstance(this).classDAO().getClassesById(Integer.parseInt(selectedClassId));
        Teacher teacher =  (Teacher) DatabaseLearningManagerSystem.getInstance(this).teacherDAO().getTeacherById(classes.getTeacherId());
        tv_teacher_name_value.setText(teacher.getTeacherName()+"");

        tv_course_details_value.setText(classes.getDescription());
        tv_price_value.setText(classes.getClassFee()+"");
    }

    private void setDataStudentClassCrossRef(Student student, Classes classes) {
        // Tạo một đối tượng StudentClassCrossRef
        StudentClassCrossRef studentClassCrossRef = new StudentClassCrossRef(student.getStudentId(), classes.getClassId(), "inactive");

        // Gọi phương thức insertStudentClassCrossRef để chèn đối tượng vào cơ sở dữ liệu
        DatabaseLearningManagerSystem.getInstance(this).studentClassCrossRefDAO().insertStudentClassCrossRef(studentClassCrossRef);
    }
    private void addEvent() {
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CourseDetailsActivity.this, ListClassesShowStudent.class);
                intent.putExtra("classCourse",classCourse);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buyCourse();
            }

        });
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buyCourse();
            }
        });
    }

    public void buyCourse() {
        //lấy id student đang tương tác với hệ thống
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String current_studentId = sharedPreferences.getString("current_studentId", "defaultValue");
        //Toast.makeText(CourseDetailsActivity.this, current_studentId, Toast.LENGTH_SHORT).show();
        // từ id lấy ra student
        Student student = DatabaseLearningManagerSystem.getInstance(CourseDetailsActivity.this).studentDAO().getStudentById(Integer.parseInt(current_studentId));
        //lấy id khóa học
        Classes classes =  (Classes) DatabaseLearningManagerSystem.getInstance(CourseDetailsActivity.this).classDAO().getClassesById(Integer.parseInt(selectedClassId));
        if (registered(student, classes) == 1) {
            try {
                dialogSure = new Dialog(CourseDetailsActivity.this);
                dialogSure.setContentView(R.layout.custom_dialog_sure);
                dialogSure.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                dialogSure.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_bg));
                dialogSure.setCancelable(false);
                dialogSure.show(); // Hiển thị dialog

                btnSure = dialogSure.findViewById(R.id.btnYes);
                btnCancel = dialogSure.findViewById(R.id.btnCancle);
                eventDialogSure(student, classes);
            } catch (Exception e) {
                Toast.makeText(CourseDetailsActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        } else if (registered(student, classes) == 2){
            Toast.makeText(CourseDetailsActivity.this, "Đang xử lý", Toast.LENGTH_SHORT).show();
        } else if (registered(student,classes) == 3) {
            Toast.makeText(CourseDetailsActivity.this, "Student are studying this class", Toast.LENGTH_SHORT).show();
        }
    }

    protected void eventDialogSure(Student student, Classes classes) {
        btnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_buyCourse = new Dialog(CourseDetailsActivity.this);
                dialog_buyCourse.setContentView(R.layout.dialog_buy_course);
                dialog_buyCourse.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog_buyCourse.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_bg));
                dialog_buyCourse.setCancelable(false);
                dialog_buyCourse.show(); // Hiển thị dialog

                btn_OK = dialog_buyCourse.findViewById(R.id.btn_OK_buy_course);
                eventDialogOK();


                setDataStudentClassCrossRef(student, classes);
                Toast.makeText(CourseDetailsActivity.this, "Successfully course registration!", Toast.LENGTH_SHORT).show();
                dialogSure.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogSure.dismiss();
            }
        });
    }

    protected void eventDialogOK() {
        btn_OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_buyCourse.dismiss();
            }
        });
    }

    protected int registered(Student student, Classes classes) {
        //kiếm dữ liệu trong db
        StudentClassCrossRef registeredCourse = DatabaseLearningManagerSystem.getInstance(this).studentClassCrossRefDAO().getStudentClassCrossRefByStudentAndCourse(student.getStudentId(), classes.getClassId());
        //nếu mà chưa đăng ký thì trả về null
        if (registeredCourse == null) {
            return 1; //học viên chưa đăng kí lớp này
        } else if (registeredCourse.getStatus().equals("active")) {
            return 3; //học viên đang học lớp này và đã được duyệt
        } else {
            return 2; //học viên đã đăng kí nhưng chưa được duyệt
        }

    }

    private void getFormWidgets() {
        btn_back = findViewById(R.id.btn_back);
        button = findViewById(R.id.button);
        btn_send = findViewById(R.id.btn_send);
        tv_teacher_name_value = findViewById(R.id.tv_teacher_name_value);
        tv_course_details_value = findViewById(R.id.tv_course_details_value);
        tv_price_value = findViewById(R.id.tv_price_value);
    }
}
