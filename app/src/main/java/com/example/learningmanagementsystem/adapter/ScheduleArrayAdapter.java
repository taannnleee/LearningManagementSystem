package com.example.learningmanagementsystem.adapter;


import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.learningmanagementsystem.R;
import com.example.learningmanagementsystem.database.DatabaseLearningManagerSystem;
import com.example.learningmanagementsystem.models.Attendance;
import com.example.learningmanagementsystem.models.Classes;
import com.example.learningmanagementsystem.models.Schedule;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ScheduleArrayAdapter extends ArrayAdapter<Schedule> {
    Activity context = null;
    ArrayList<Schedule> myArray = null;
    int layoutId;
    Classes classes;
    public ScheduleArrayAdapter(@NonNull Activity context, int layoutId, ArrayList<Schedule> arr, Classes classes){
        super(context, layoutId,arr);
        this.context = context;
        this.myArray = arr;
        this.layoutId = layoutId;
        this.classes = classes;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.item_schedule_layout, null, true);


        TextView txttopicClass = listItemView.findViewById(R.id.txttopicClass);
        TextView txtNameCourse = listItemView.findViewById(R.id.txtNameCourse);
        TextView txttime = listItemView.findViewById(R.id.txttime);
        Button btnAbsent = listItemView.findViewById(R.id.btnabsent);

        btnAbsent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Schedule  schedule   = myArray.get(position);
                // Tạo một Dialog mới
                Dialog dialog = new Dialog(context);
                // Đặt layout cho Dialog
                dialog.setContentView(R.layout.dialog_layout);

                // Ánh xạ các phần tử trong layout của Dialog
                EditText editTextReason = dialog.findViewById(R.id.editTextReason);
                Button btnCancel = dialog.findViewById(R.id.btnCancel);
                Button btnConfirm = dialog.findViewById(R.id.btnConfirm);

                // Thiết lập sự kiện cho nút Cancel
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Đóng Dialog khi người dùng nhấn nút Cancel
                        dialog.dismiss();
                    }
                });

                // Thiết lập sự kiện cho nút Confirm
                btnConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Lấy nội dung từ EditText
                        String reason = editTextReason.getText().toString();
                        Toast.makeText(context, schedule.getClassScheduleCode(), Toast.LENGTH_SHORT).show();
                        if (reason.equals(schedule.getClassScheduleCode())) {
                            Attendance attendance = new Attendance();
                            attendance.setScheduleId(schedule.getScheduleId());
                            attendance.setStatus("active");

                            DatabaseLearningManagerSystem.getInstance(context).attendanceDAO().insertAttendance();

                            Toast.makeText(context, "Absent Success", Toast.LENGTH_SHORT).show();
                        } else {

                            Toast.makeText(context, "Absent Fail", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();

                    }
                });

                // Hiển thị Dialog
                dialog.show();
            }
        });


        txttopicClass.setText(classes.getClassCourse());
        txtNameCourse.setText(classes.getClassName());

        Schedule  schedule   = myArray.get(position);
        Date dateSpecificDate = schedule.getSpecificDate();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDateStart = sdf.format(dateSpecificDate);
        txttime.setText(formattedDateStart);

        return listItemView;
    }
}

