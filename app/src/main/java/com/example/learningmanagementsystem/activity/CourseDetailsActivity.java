package com.example.learningmanagementsystem.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
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

import java.util.ArrayList;

public class CourseDetailsActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private ImageButton btn_back;
    private  ArrayList<Classes> arrClasses;
    String classCourse;
    String selectedClassId;
    Button button, btn_send;
    TextView tv_teacher_name_value, tv_price_value, tv_course_details_value;
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
                //lấy id student đang tương tác với hệ thống
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String current_studentId = sharedPreferences.getString("current_studentId", "defaultValue");
                Toast.makeText(CourseDetailsActivity.this, current_studentId, Toast.LENGTH_SHORT).show();
                // từ id lấy ra student
                Student student = DatabaseLearningManagerSystem.getInstance(CourseDetailsActivity.this).studentDAO().getStudentById(Integer.parseInt(current_studentId));
                //lấy id khóa học
                Classes classes =  (Classes) DatabaseLearningManagerSystem.getInstance(CourseDetailsActivity.this).classDAO().getClassesById(Integer.parseInt(selectedClassId));
                setDataStudentClassCrossRef(student, classes);


            }

        });
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
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
