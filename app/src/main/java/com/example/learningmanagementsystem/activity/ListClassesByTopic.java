    package com.example.learningmanagementsystem.activity;

    import androidx.appcompat.app.AppCompatActivity;

    import android.app.Dialog;
    import android.os.Bundle;
    import android.view.Gravity;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.AdapterView;
    import android.widget.ListView;
    import android.widget.PopupWindow;
    import android.widget.TextView;
    import android.widget.Toast;

    import com.example.learningmanagementsystem.R;
    import com.example.learningmanagementsystem.adapter.ClassesArrayAdapter;
    import com.example.learningmanagementsystem.database.DatabaseLearningManagerSystem;
    import com.example.learningmanagementsystem.models.Classes;

    import java.util.ArrayList;

    public class ListClassesByTopic extends AppCompatActivity {
        ArrayList<Classes> arrClasses = new ArrayList<Classes>();
        ClassesArrayAdapter adapter = null;
        ListView lvClass = null;
        Dialog dialog;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_list_classes_by_topic);
            lvClass = findViewById(R.id.lvclasses);
            arrClasses = (ArrayList<Classes>) DatabaseLearningManagerSystem.getInstance(this).classDAO().getAllClasst();
            showListClass();
            lvClass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    dialog = new Dialog(ListClassesByTopic.this);
                    dialog.setContentView(R.layout.custom_dialog_box);
                    dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                    dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_dialog_bg));
                    dialog.setCancelable(false);
                    dialog.show(); // Hiển thị dialog
                }
            });
            dialog.getWindow().getDecorView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss(); // Đóng dialog khi nhấn ra ngoài
                }
            });

        }



        private void showListClass() {
            adapter = new ClassesArrayAdapter(this, R.layout.item_classes_layout,arrClasses);
            lvClass.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }