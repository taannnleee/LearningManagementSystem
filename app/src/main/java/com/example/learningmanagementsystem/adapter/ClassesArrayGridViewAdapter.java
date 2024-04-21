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

public class ClassesArrayGridViewAdapter extends ArrayAdapter<Classes> {
    Activity context;
    int resource;

    public ClassesArrayGridViewAdapter(@NonNull Activity context, int resource){
        super(context,resource);
        this.context = context;
        this.resource = resource;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater inflater = this.context.getLayoutInflater();
        View productView = inflater.inflate(this.resource, null);

        TextView txtClassName = productView.findViewById(R.id.txtClassesName);

        Classes classes  = getItem(position);
        txtClassName.setText (classes.getClassName());
        return productView;
    }
}
