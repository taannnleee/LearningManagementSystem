    package com.example.learningmanagementsystem.activity;

    import androidx.appcompat.app.AppCompatActivity;

    import android.app.Dialog;
    import android.content.Intent;
    import android.os.Bundle;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.AdapterView;
    import android.widget.Button;
    import android.widget.GridView;
    import android.widget.ListView;
    import android.widget.Toast;

    import com.example.learningmanagementsystem.R;
    import com.example.learningmanagementsystem.adapter.ClassesArrayAdapter;
    import com.example.learningmanagementsystem.adapter.ClassesArrayGridViewAdapter;
    import com.example.learningmanagementsystem.database.DatabaseLearningManagerSystem;
    import com.example.learningmanagementsystem.models.Classes;

    import java.util.ArrayList;

    public class ListClassesShowStudent extends AppCompatActivity {
        ArrayList<Classes> arrClasses = new ArrayList<Classes>();
        ClassesArrayAdapter adapter = null;
        ListView lvClass = null;
        Dialog dialog;
        Button back_button;
        String classCourse;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_list_classes_show_student);
            getFormWidgets();
            addEvent();



            Intent intent = getIntent();
            classCourse = intent.getStringExtra("classCourse");

            arrClasses = (ArrayList<Classes>) DatabaseLearningManagerSystem.getInstance(this).classDAO().getClassesByClassCourse(classCourse);
            adapter = new ClassesArrayAdapter(ListClassesShowStudent.this, R.layout.item_classes_layout,arrClasses);
            lvClass.setAdapter(adapter);

        }

        private void addEvent() {
            back_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ListClassesShowStudent.this, InteractionActivity.class);
                    startActivity(intent);
                }
            });

            lvClass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Classes selectedClass = arrClasses.get(position);
                    int selectedClassId = selectedClass.getClassId();
                    // Tạo Intent
                    Intent intent = new Intent(ListClassesShowStudent.this, CourseDetailsActivity.class);

                    // Đính kèm dữ liệu
                    intent.putExtra("classCourse",classCourse);
                    intent.putExtra("selectedClassId", String.valueOf(selectedClassId));

                    startActivity(intent);
                }
            });
        }

        private void getFormWidgets() {
            lvClass = findViewById(R.id.lvData);
            back_button = findViewById(R.id.back_button);
        }
    }