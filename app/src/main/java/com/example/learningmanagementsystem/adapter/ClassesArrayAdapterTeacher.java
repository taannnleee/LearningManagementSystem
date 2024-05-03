package com.example.learningmanagementsystem.adapter;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ClassesArrayAdapterTeacher extends ArrayAdapter<Classes> {
    Activity context = null;
    ArrayList<Classes> myArray = null;
    int layoutId;
    public ClassesArrayAdapterTeacher(@NonNull Activity context, int layoutId, ArrayList<Classes> arr){
        super(context, layoutId,arr);
        this.context = context;
        this.myArray = arr;
        this.layoutId = layoutId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.item_classes_layout_teacher, null, true);

        TextView txttopicClass = listItemView.findViewById(R.id.txttopicClass);
        TextView  txtName = listItemView.findViewById(R.id.txtNameCourse);
        TextView txttime = listItemView.findViewById(R.id.txttime);

        ImageView imageClasses = listItemView.findViewById(R.id.imageClasses);


        Classes  classes   = myArray.get(position);

        txtName.setText(classes.getClassName());
        txttopicClass.setText(classes.getClassCourse());

        // Chuyển đổi byte[] thành Bitmap
        byte[] byteArray = classes.getClassPicture();
        if(byteArray != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            imageClasses.setImageBitmap(bitmap);
        } else {
        }



        Date date_start = classes.getCourseStart();
        Log.e("ok", date_start.toString());

        Date date_end = classes.getCourseEnd();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDateStart = sdf.format(date_start);
        String formattedDateEnd = sdf.format(date_end);
        txttime.setText(formattedDateStart + " - "+formattedDateEnd);

        return listItemView;
    }
}

