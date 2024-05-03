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
import com.example.learningmanagementsystem.models.Classes;
import com.example.learningmanagementsystem.models.Schedule;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ScheduleArrayAdapterTeacher extends ArrayAdapter<Schedule> {
    Activity context = null;
    ArrayList<Schedule> myArray = null;
    int layoutId;
    Classes classes;
    public ScheduleArrayAdapterTeacher(@NonNull Activity context, int layoutId, ArrayList<Schedule> arr, Classes classes){
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
        View listItemView = inflater.inflate(R.layout.item_schedule_layout_teacher, null, true);


        TextView txttopicClass = listItemView.findViewById(R.id.txttopicClass);
        TextView tvcode = listItemView.findViewById(R.id.tvcode);
        TextView txttime = listItemView.findViewById(R.id.txttime);




        txttopicClass.setText(classes.getClassCourse());


        Schedule  schedule   = myArray.get(position);
        Date dateSpecificDate = schedule.getSpecificDate();
        tvcode.setText(schedule.getClassScheduleCode());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDateStart = sdf.format(dateSpecificDate);
        txttime.setText(formattedDateStart);

        return listItemView;
    }
}

