package com.example.learningmanagementsystem.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learningmanagementsystem.MainTeacherActivity;
import com.example.learningmanagementsystem.R;
import com.example.learningmanagementsystem.dao.AdminDAO;
import com.example.learningmanagementsystem.dao.StudentDAO;
import com.example.learningmanagementsystem.dao.TeacherDAO;
import com.example.learningmanagementsystem.database.DatabaseLearningManagerSystem;
import com.example.learningmanagementsystem.models.Admin;
import com.example.learningmanagementsystem.models.Student;
import com.example.learningmanagementsystem.models.Teacher;

public class LoginActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnLogin; // Initialize Button object
    private TextView txtviewregister;
    private RadioButton radioStudent, radioTeacher, radioAdmin;
    private String selectedRole = "";


    private void initPreferences() {

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getFormWidgets(); // Call this method first to initialize widgets
        addEvent();
        initPreferences();

    }

    private void setPreferences( Student student) {
        editor.putString("current_studentId",student.getStudentId()+"" );
        editor.commit();
    }

    public void addEvent() {
        // Xử lý sự kiện khi nhấn nút Đăng nhập
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = setStudentData();
                Teacher teacher = setTeacherData();
                Admin admin = setAdminData();
                if (!isValidEmail(student.getStudentEmail())) {
                    Toast.makeText(LoginActivity.this, "Invalid email address", Toast.LENGTH_SHORT).show();
                } else if (student.getStudentPassword().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
                } else if(selectedRole == "student"){
                    loginStudent(student);
                } else if(selectedRole =="teacher"){
                    loginTeacher(teacher);
                } else if(selectedRole =="admin"){
                    loginAdmin(admin);
                } else {
                    Toast.makeText(LoginActivity.this, "Please fill in the information accurately", Toast.LENGTH_SHORT).show();
                }
            }
        });
        txtviewregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
        radioStudent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    selectedRole = "student";
                }
            }
        });

        radioTeacher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    selectedRole = "teacher";

                }
            }
        });

        radioAdmin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    selectedRole = "admin";

                }
            }
        });

    }

    private void getFormWidgets() {
        edtEmail = findViewById(R.id.login_email);
        edtPassword = findViewById(R.id.login_password);
        btnLogin = findViewById(R.id.btnYes);
        txtviewregister = findViewById(R.id.loginRedirectText);
        radioStudent = findViewById(R.id.radio_student);
        radioTeacher = findViewById(R.id.radio_teacher);
        radioAdmin = findViewById(R.id.radio_admin);
    }

    private Student setStudentData(){
        Student newStudent = new Student();
        newStudent.setStudentEmail(edtEmail.getText().toString());
        newStudent.setStudentPassword(edtPassword.getText().toString());
        return newStudent;
    }
    private Teacher setTeacherData(){
        Teacher newTeacher = new Teacher();
        newTeacher.setTeacherEmail(edtEmail.getText().toString());
        newTeacher.setTeacherPassword(edtPassword.getText().toString());
        return newTeacher;
    }
    private Admin setAdminData(){
        Admin newAdmin = new Admin();
        newAdmin.setAdminEmail(edtEmail.getText().toString());
        newAdmin.setAdminPassword(edtPassword.getText().toString());
        return newAdmin;
    }

    private boolean isValidEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
        String emailPatternPart2 = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+";
        String emailPatternPart3 = "[a-zA-Z0-9._-]+@";
        String emailPatternPart4 = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]";
        if(email.matches(emailPattern) || email.matches(emailPatternPart2) || email.matches(emailPatternPart3) || email.matches(emailPatternPart4))
            return true;
        else return false;
    }

    private void loginStudent(Student student) {
        StudentDAO studentDAO = DatabaseLearningManagerSystem.getInstance(this).studentDAO();
        Student existingStudent = studentDAO.getStudentByEmail(student.getStudentEmail());

        if (existingStudent != null) {

            if (existingStudent.getStudentPassword().equals(student.getStudentPassword())) {

                setPreferences(existingStudent);
                Intent intent = new Intent(LoginActivity.this, InteractionActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Incorrect student password", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(LoginActivity.this, "Email student does not exist", Toast.LENGTH_SHORT).show();
        }
    }

    private void loginTeacher(Teacher teacher) {
        TeacherDAO teacherDAO = DatabaseLearningManagerSystem.getInstance(this).teacherDAO();
        Teacher existingTeacher = teacherDAO.getTeacherByEmail(teacher.getTeacherEmail());

        if (existingTeacher != null) {

            if (existingTeacher.getTeacherPassword().equals(teacher.getTeacherPassword())) {

                Intent intent = new Intent(LoginActivity.this, MainTeacherActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Incorrect teacher password", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(LoginActivity.this, "Email teacher does not exist", Toast.LENGTH_SHORT).show();
        }
    }

    private void loginAdmin(Admin admin) {
        AdminDAO adminDAO = DatabaseLearningManagerSystem.getInstance(this).adminDAO();
        Admin existingAdmin = adminDAO.getAdminByEmail(admin.getAdminEmail());

        if (existingAdmin != null) {

            if (existingAdmin.getAdminPassword().equals(admin.getAdminPassword())) {
                Intent intent = new Intent(LoginActivity.this, InteractionAdminActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Incorrect admin password", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(LoginActivity.this, "Email admin does not exist", Toast.LENGTH_SHORT).show();
        }
    }

}
