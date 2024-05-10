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
import com.example.learningmanagementsystem.models.Post;
import com.example.learningmanagementsystem.models.Support;

import java.util.ArrayList;

public class SupportArrayAdapter extends ArrayAdapter<Support> {

    Activity context = null;
    ArrayList<Support> myArray = null;
    int layoutId;

    public SupportArrayAdapter(@NonNull Activity context, int layoutId, ArrayList<Support> arr) {
        super(context, layoutId,arr);
        this.context = context;
        this.myArray = arr;
        this.layoutId = layoutId;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.announcements_item, null, true);

        TextView textViewTitle = listItemView.findViewById(R.id.textViewTitle);
        TextView textViewContent = listItemView.findViewById(R.id.textViewContent);
        Support support  = myArray.get(position);

        textViewTitle.setText(support.getSupportTitle());
        textViewContent.setText(support.getSupportContent());
        return listItemView;
    }
}
