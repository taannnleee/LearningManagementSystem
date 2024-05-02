package com.example.learningmanagementsystem.adapter;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


public class ViewPageAdapter extends FragmentStatePagerAdapter {


    public ViewPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new BlankFragment();
            case 1:
                return new InteractionActivity();
            case 2:
                return new BlankFragment3();
            case 3:
                return new BlankFragment4();
            default:
                return new BlankFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
