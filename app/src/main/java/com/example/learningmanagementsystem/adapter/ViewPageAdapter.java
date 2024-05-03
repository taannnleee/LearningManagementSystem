package com.example.learningmanagementsystem.adapter;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.learningmanagementsystem.activity.AccountActivity;
import com.example.learningmanagementsystem.activity.InteractionActivity;
import com.example.learningmanagementsystem.activity.MainActivity;


public class ViewPageAdapter extends FragmentStatePagerAdapter {


    public ViewPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new MainActivity();
            case 1:
                return new InteractionActivity();
            case 3:
                return new AccountActivity();
            default:
                return new MainActivity();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
