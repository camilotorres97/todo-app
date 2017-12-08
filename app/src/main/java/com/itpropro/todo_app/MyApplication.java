package com.itpropro.todo_app;

import android.app.Application;

import com.itpropro.todo_app.database.AppDatabase;

/**
 * Created by juank on 8/12/2017.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();

        AppDatabase.init(getApplicationContext());
    }
}
