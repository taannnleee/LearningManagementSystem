    package com.example.learningmanagementsystem.activity;

    import androidx.appcompat.app.AppCompatActivity;

    import android.app.Dialog;
    import android.os.Bundle;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.AdapterView;
    import android.widget.GridView;
    import android.widget.ListView;

    import com.example.learningmanagementsystem.R;
    import com.example.learningmanagementsystem.adapter.ClassesArrayAdapter;
    import com.example.learningmanagementsystem.adapter.ClassesArrayGridViewAdapter;
    import com.example.learningmanagementsystem.database.DatabaseLearningManagerSystem;
    import com.example.learningmanagementsystem.models.Classes;

    import java.util.ArrayList;

    public class ListClassesShowStudent extends AppCompatActivity {
        ArrayList<Classes> arrClasses = new ArrayList<Classes>();
        ClassesArrayGridViewAdapter adapter = null;
        GridView gvClass = null;
        Dialog dialog;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_list_classes_show_student);

            gvClass = findViewById(R.id.gird_view);
            adapter = new ClassesArrayGridViewAdapter(this, R.layout.item_gird_class_layout);


            arrClasses = (ArrayList<Classes>) DatabaseLearningManagerSystem.getInstance(this).classDAO().getAllClasses();
            adapter.addAll(arrClasses);



            showListClass();
//            lvClass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    dialog = new Dialog(ListClassesShowStudent.this);
//                    dialog.setContentView(R.layout.custom_dialog_box);
//                    dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
//                    dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_bg));
//                    dialog.setCancelable(false);
//                    dialog.show(); // Hiển thị dialog
//                }
//            });
//            dialog.getWindow().getDecorView().setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dialog.dismiss(); // Đóng dialog khi nhấn ra ngoài
//                }
//            });

        }

        private void showListClass() {
            gvClass.setAdapter(adapter);
        }
    }