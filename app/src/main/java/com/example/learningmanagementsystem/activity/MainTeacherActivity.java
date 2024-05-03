package com.example.learningmanagementsystem.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.learningmanagementsystem.R;
import com.example.learningmanagementsystem.ShowSheduleTeacherActivity;
import com.example.learningmanagementsystem.adapter.ClassesArrayAdapter;
import com.example.learningmanagementsystem.adapter.ClassesArrayAdapterTeacher;
import com.example.learningmanagementsystem.database.DatabaseLearningManagerSystem;
import com.example.learningmanagementsystem.models.Classes;

import java.util.ArrayList;

public class MainTeacherActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ArrayList<Classes> arrClasses = new ArrayList<Classes>();
    ClassesArrayAdapterTeacher adapter = null;
    ListView lvListClass = null;
    Dialog dialog;
    Button back_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_teacher);

        getFormWidgets();
        addEvent();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String current_teacherId = sharedPreferences.getString("current_teacher_Id", "defaultValue");


        arrClasses = (ArrayList<Classes>) DatabaseLearningManagerSystem.getInstance(this).classDAO().getClassesByTeacherId(Integer.parseInt(current_teacherId));
        adapter = new ClassesArrayAdapterTeacher(MainTeacherActivity.this, R.layout.item_classes_layout_teacher,arrClasses);
        lvListClass.setAdapter(adapter);

        registerForContextMenu(lvListClass);
    }
    private void addEvent() {

//        lvListClass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Classes selectedClass = arrClasses.get(position);
//                String selectedClassId = String.valueOf(selectedClass.getClassId());
//                // Tạo Intent
//                Intent intent = new Intent(MainTeacherActivity.this, ShowListStudentTeacherActivity.class);
//                intent.putExtra("selectedClassId", selectedClassId);
//                startActivity(intent);
//
//            }
//        });
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainTeacherActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getFormWidgets() {
        lvListClass = findViewById(R.id.lvListClass);
        back_button = findViewById(R.id.back_button);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.contextmenu, menu);
    }

    private static final int MENU_ITEM_QLTG = R.id.mnuqltg;
    private static final int MENU_ITEM_DANH_SACH_SV = R.id.mnuds;
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();
        if (itemId == MENU_ITEM_DANH_SACH_SV) {
            // Lấy vị trí của mục trong danh sách
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            int position = info.position;
            // Lấy lớp được chọn từ danh sách
            Classes selectedClass = arrClasses.get(position);
            String selectedClassId = String.valueOf(selectedClass.getClassId());
            // Chuyển hướng đến màn hình "ShowListStudentTeacherActivity" và truyền dữ liệu
            Intent intent = new Intent(MainTeacherActivity.this, ShowListStudentTeacherActivity.class);
            intent.putExtra("selectedClassId", selectedClassId);
            startActivity(intent);

            return true;
        } else if (itemId == MENU_ITEM_QLTG) {

            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            int position = info.position;
            // Lấy lớp được chọn từ danh sách
            Classes selectedClass = arrClasses.get(position);
            String selectedClassId = String.valueOf(selectedClass.getClassId());
            // Chuyển hướng đến màn hình "ShowListStudentTeacherActivity" và truyền dữ liệu
            Intent intent = new Intent(MainTeacherActivity.this, ShowSheduleTeacherActivity.class);
            intent.putExtra("selectedClassId", selectedClassId);
            startActivity(intent);

            return true;
        }
        return super.onContextItemSelected(item);
    }
}