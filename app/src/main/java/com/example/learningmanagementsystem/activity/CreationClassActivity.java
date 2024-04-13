package com.example.learningmanagementsystem.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.learningmanagementsystem.R;
import com.example.learningmanagementsystem.database.DatabaseLearningManagerSystem;
import com.example.learningmanagementsystem.models.Classes;
import com.example.learningmanagementsystem.models.Teacher;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreationClassActivity extends AppCompatActivity {

    EditText et_className, et_classSize, et_courseStart,
            et_courseEnd, et_studyingDates, et_classStart, et_classEnd;
    AutoCompleteTextView cmbBox_course, cmbBox_teacherInfo;
    Button btn_createClass, btn_clearText;
    TextView btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_creation_class);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        getFormWidget();
        btn_createClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    CreateClass();
                }
                catch (Exception e) {
                    Toast.makeText(CreationClassActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void getFormWidget() {
        et_className = findViewById(R.id.et_class_name);
        et_classSize = findViewById(R.id.et_class_size);
        et_courseStart = findViewById(R.id.et_class_starting_date);
        et_courseEnd = findViewById(R.id.et_class_ending_date);
        et_studyingDates = findViewById(R.id.et_studying_date);
        et_classStart = findViewById(R.id.et_starting_time);
        et_classEnd = findViewById(R.id.et_finishing_time);
        cmbBox_course = findViewById(R.id.auto_compl_tv_course);
        cmbBox_teacherInfo = findViewById(R.id.auto_compl_tv_teacher_name);
        btn_createClass = findViewById(R.id.btn_createClass);
        btn_clearText = findViewById(R.id.btn_clearText);
        btn_back = findViewById(R.id.btn_create_class_back);

        List<Teacher> allTeachers = new ArrayList<>();
        allTeachers = DatabaseLearningManagerSystem.getInstance(this).teacherDAO().getAllTeacher();
        ArrayAdapter<Teacher> teachersAdapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, allTeachers);
        cmbBox_teacherInfo.setAdapter(teachersAdapter);
        cmbBox_teacherInfo.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View v) {
                cmbBox_teacherInfo.showDropDown();
            }
        });

        String[] array = {"TOIEC", "IELTS", "Giao tiếp cơ bản"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, array);
        cmbBox_course.setAdapter(adapter);
        cmbBox_course.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View v) {
                cmbBox_course.showDropDown();
            }
        });
    }

    public void CreateClass() throws ParseException {
        Classes newClass = SetClassData();
        DatabaseLearningManagerSystem.getInstance(this).classDAO().insertNewClass(newClass);
    }

    public Classes SetClassData() throws ParseException {
        String name = et_className.getText().toString();
        String course = cmbBox_course.getText().toString();
        int size = Integer.valueOf(et_classSize.getText().toString());
        String teacherInfo = cmbBox_teacherInfo.getText().toString();
        Teacher teacher = FindTeacher(teacherInfo);
        String temp_courseStart = et_courseStart.getText().toString();
        Date courseStart = new SimpleDateFormat("dd/MM/yyyy").parse(temp_courseStart);
        String temp_courseEnd = et_courseEnd.getText().toString();
        Date courseEnd = new SimpleDateFormat("dd/MM/yyyy").parse(temp_courseEnd);
        String studyDates = et_studyingDates.getText().toString();
        String temp_classStart = et_classStart.getText().toString();
        Date classStart = new SimpleDateFormat("hh:mm").parse(temp_classStart); //thêm giờ sai
        String temp_classEnd = et_classEnd.getText().toString();
        Date classEnd = new SimpleDateFormat("hh:mm").parse(temp_classEnd); //thêm giờ sai
        Classes newClass = new Classes(name, course, size, teacher.getTeacherId(), courseStart, courseEnd, studyDates, classStart, classEnd);
        return newClass;
    }

    public Teacher FindTeacher(String teacherInfo) {
        char temp_id = teacherInfo.charAt(0);
        int id = Character.getNumericValue(temp_id);
        return DatabaseLearningManagerSystem.getInstance(this).teacherDAO().getTeacherById(id);
    }
}