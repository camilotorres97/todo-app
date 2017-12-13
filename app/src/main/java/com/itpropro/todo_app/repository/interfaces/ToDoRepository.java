package com.itpropro.todo_app.repository.interfaces;

import com.itpropro.todo_app.domain.model.Todo;

import java.util.List;

/**
 * Created by juank on 7/12/2017.
 */

public interface ToDoRepository {

    Long insert(Todo todo);

    void update(Todo todo);

    void delete(Todo todo);

    List<Todo> getAll();
}
