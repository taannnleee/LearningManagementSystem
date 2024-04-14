package com.example.learningmanagementsystem.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.learningmanagementsystem.R;
import com.example.learningmanagementsystem.models.Classes;

import java.util.ArrayList;

public class ClassesArrayAdapter extends ArrayAdapter<Classes> {
    Activity context = null;
    ArrayList<Classes> myArray = null;
    int layoutId;
    public ClassesArrayAdapter(@NonNull Activity context, int layoutId, ArrayList<Classes> arr){
        super(context, layoutId,arr);
        this.context = context;
        this.myArray = arr;
        this.layoutId = layoutId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutId, null);
        if(myArray.size()>0 && position >=0){
            final TextView txtdisplay = (TextView)convertView.findViewById(R.id.txtitem);

            final Classes emp = myArray.get(position);
            txtdisplay.setText(emp.getClassDuration());
//            txtdisplay.setText(emp.toString());
        }
        return convertView;
    }
}
