package com.example.learningmanagementsystem.adapter;

import android.app.Activity;
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
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.item_classes_layout, null, true);

        TextView tvshow = listItemView.findViewById(R.id.txtitem);


        Classes  classes   = myArray.get(position);

        tvshow.setText(classes.getClassName());
        return listItemView;
    }
}
