package com.itpropro.todo_app.database;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by juank on 7/12/2017.
 */

public class TypeTransmogrifiers {
    @TypeConverter
    public static Long fromDate(Date date){
        if(date == null){
            return (null);
        }

        return (date.getTime());
    }

    @TypeConverter
    public static Date toDate(Long millisSiceEpoch){
        if(millisSiceEpoch == null){
            return (null);
        }
        return (new Date(millisSiceEpoch));
    }
}
