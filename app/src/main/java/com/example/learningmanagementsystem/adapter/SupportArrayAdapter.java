package com.example.learningmanagementsystem.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.learningmanagementsystem.R;
import com.example.learningmanagementsystem.models.Support;

import java.util.ArrayList;

public class SupportArrayAdapter extends ArrayAdapter<Support> {

    Activity context = null;
    ArrayList<Support> myArray = null;
    int layoutId;

    public SupportArrayAdapter(@NonNull Activity context, int layoutId, ArrayList<Support> arr) {
        super(context, layoutId, arr);
        this.context = context;
        this.myArray = arr;
        this.layoutId = layoutId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listItemView = inflater.inflate(layoutId, null, true);

        TextView textViewContent = listItemView.findViewById(R.id.textViewContent);
        ImageView imgVSupport = listItemView.findViewById(R.id.imageView);
        Support support = myArray.get(position);

        textViewContent.setText(support.getSupportContent());

        // Convert byte array to Bitmap and set it to the ImageView
        Bitmap bitmap = BitmapFactory.decodeByteArray(support.getPictureSupport(), 0, support.getPictureSupport().length);
        imgVSupport.setImageBitmap(bitmap);

        return listItemView;
    }
}
