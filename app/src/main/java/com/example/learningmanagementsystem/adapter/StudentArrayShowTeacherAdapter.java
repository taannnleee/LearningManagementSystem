package com.example.learningmanagementsystem.adapter;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.learningmanagementsystem.R;
import com.example.learningmanagementsystem.models.Classes;
import com.example.learningmanagementsystem.models.Student;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class StudentArrayShowTeacherAdapter extends ArrayAdapter<Student> {
    Activity context = null;
    ArrayList<Student> myArray = null;
    int layoutId;
    public StudentArrayShowTeacherAdapter(@NonNull Activity context, int layoutId, ArrayList<Student> arr){
        super(context, layoutId,arr);
        this.context = context;
        this.myArray = arr;
        this.layoutId = layoutId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.student_show_teacher_item, null, true);

        TextView tv_Id = listItemView.findViewById(R.id.tv_Id);
        TextView  tv_StudentName = listItemView.findViewById(R.id.tv_StudentName);
        TextView tv_address = listItemView.findViewById(R.id.tv_address);



        Student student   = myArray.get(position);

        tv_Id.setText(student.getStudentId()+"");
        tv_StudentName.setText(student.getStudentName());
        tv_address.setText(student.getStudentAddress());

        return listItemView;
    }
}

