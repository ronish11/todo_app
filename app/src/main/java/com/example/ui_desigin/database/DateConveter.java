package com.example.ui_desigin.database;

import androidx.room.TypeConverter;

import java.util.Date;

public class DateConveter {

    @TypeConverter
    public static Long toTimestamp(Date date){
        return date ==null? null: date.getTime();
    }

    @TypeConverter
    public static Date toDate(Long timestamp){
        return timestamp==null? null : new Date(timestamp);
    }
}
