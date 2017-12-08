package com.itpropro.todo_app.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.itpropro.todo_app.domain.model.Todo;
import com.itpropro.todo_app.repository.impl.ToDoLocalRepository;

/**
 * Created by juank on 7/12/2017.
 */
@Database(entities = {Todo.class}, version = 1)
@TypeConverters({TypeTransmogrifiers.class})
public abstract class AppDatabase extends RoomDatabase{
    private static AppDatabase db;

    public static void init(Context context){
        if(db == null){
            db = Room.databaseBuilder(context.getApplicationContext(),
            AppDatabase.class, "todo-app").build();
        }
    }

    public static AppDatabase getInstance(){
        return db;
    }

    public abstract ToDoLocalRepository.ToDoDao todoDao();
}
