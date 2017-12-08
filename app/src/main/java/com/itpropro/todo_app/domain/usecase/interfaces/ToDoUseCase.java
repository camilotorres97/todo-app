package com.itpropro.todo_app.domain.usecase.interfaces;

import com.itpropro.todo_app.domain.model.Todo;
import com.itpropro.todo_app.helpers.Callback;

import java.util.Date;
import java.util.List;

/**
 * Created by juank on 7/12/2017.
 */

public interface ToDoUseCase {

    void insert(String description, Date finishDate, Boolean finished, String image, Integer color, Callback<Todo> callback);

    void update(Todo todo, Callback<Todo> callback);

    void delete(Todo todo, Callback<Boolean> callback);

    void getAll(Callback<List<Todo>> callback);
}
