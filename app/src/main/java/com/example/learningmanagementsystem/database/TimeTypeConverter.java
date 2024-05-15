package com.example.learningmanagementsystem.database;

import androidx.room.TypeConverter;

import java.sql.Time;

public class TimeTypeConverter {
    @TypeConverter
    public static Time toTime(Long timeLong){
        return timeLong == null ? null : new Time(timeLong);
    }

    @TypeConverter
    public static Long fromTime(Time time){
        return time == null ? null : time.getTime();
    }
}
