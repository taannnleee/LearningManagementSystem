package com.example.learningmanagementsystem.adapter;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.learningmanagementsystem.InformationNoCourse;
import com.example.learningmanagementsystem.activity.AccountActivity;
import com.example.learningmanagementsystem.activity.InteractionActivity;
import com.example.learningmanagementsystem.activity.MainActivity;
import com.example.learningmanagementsystem.database.DatabaseLearningManagerSystem;


public class ViewPageAdapter extends FragmentStatePagerAdapter {

    private Context mContext;
    public ViewPageAdapter(@NonNull FragmentManager fm, int behavior,  Context context) {
        super(fm, behavior);
        mContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        String current_studentId = sharedPreferences.getString("current_studentId", "defaultValue");
        switch (position){
            case 0:
                int count = DatabaseLearningManagerSystem.getInstance(mContext).studentClassCrossRefDAO().countStudent(Integer.parseInt(current_studentId), "active");
                if(count == 0){
                    return new InformationNoCourse();
                }else {
                    return new MainActivity();
                }

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
