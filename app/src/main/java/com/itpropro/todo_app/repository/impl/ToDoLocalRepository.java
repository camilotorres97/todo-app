package com.itpropro.todo_app.repository.impl;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.itpropro.todo_app.database.AppDatabase;
import com.itpropro.todo_app.domain.model.Todo;
import com.itpropro.todo_app.repository.interfaces.ToDoRepository;

import java.util.List;

/**
 * Created by juank on 7/12/2017.
 */

public class ToDoLocalRepository implements ToDoRepository {

    @Dao
    public interface ToDoDao{
        @Insert
        void insert(Todo todo);

        @Update
        void update(Todo todo);

        @Delete
        void delete(Todo todo);

        @Query("SELECT * FROM todo")
        List<Todo> getAll();
    }

    @Override
    public Long insert(Todo todo) {
        AppDatabase db = AppDatabase.getInstance();
        ToDoDao toDoDao = db.todoDao();
        toDoDao.insert(todo);
        return null;
    }

    @Override
    public void update(Todo todo) {
        AppDatabase db = AppDatabase.getInstance();
        ToDoDao toDoDao = db.todoDao();
        toDoDao.update(todo);
    }

    @Override
    public void delete(Todo todo) {
        AppDatabase  db = AppDatabase.getInstance();
        ToDoDao toDoDao = db.todoDao();
        toDoDao.delete(todo);
    }

    @Override
    public List<Todo> getAll() {
        AppDatabase db = AppDatabase.getInstance();
        ToDoDao toDoDao = db.todoDao();
        return toDoDao.getAll();
    }
}
