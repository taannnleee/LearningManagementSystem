package com.example.learningmanagementsystem.adapter;

import android.app.Activity;
import android.content.Context;
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
import com.example.learningmanagementsystem.models.Post;

import java.util.ArrayList;

public class PostArrayAdapter extends ArrayAdapter<Post> {
    Activity context = null;
    ArrayList<Post> myArray = null;
    int layoutId;

    public PostArrayAdapter(@NonNull Activity context, int layoutId, ArrayList<Post> arr) {
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
        Post post  = myArray.get(position);

        textViewTitle.setText(post.getPostTitle());
        textViewContent.setText(post.getPostContent());
        return listItemView;
    }
}
