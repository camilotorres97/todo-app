package com.itpropro.todo_app.domain.usecase.impl;

import com.itpropro.todo_app.domain.model.Todo;
import com.itpropro.todo_app.domain.usecase.interfaces.ToDoUseCase;
import com.itpropro.todo_app.helpers.Callback;
import com.itpropro.todo_app.helpers.ThreadExecutor;
import com.itpropro.todo_app.repository.impl.ToDoLocalRepository;
import com.itpropro.todo_app.repository.interfaces.ToDoRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by juank on 7/12/2017.
 */

public class ToDoUseCaseImpl implements ToDoUseCase {

    private ToDoRepository toDoRepository;

    public  ToDoUseCaseImpl(){
        this.toDoRepository = new ToDoLocalRepository();
    }

    @Override
    public void insert(final String description, final Date finishDate, final Boolean finished, final String image,
                       final Integer color, final Callback<Todo> callback){
        new ThreadExecutor<Todo>(new ThreadExecutor.Task<Todo>(){
            @Override
            public Todo execute() throws Exception{
                Todo todo = new Todo(description,finishDate, finished, image, color);
                toDoRepository.insert(todo);
                return todo;
            }

            @Override
            public void finish(Exception error, Todo result){
                if(error != null){
                    callback.error(error);
                }else{
                    callback.success(result);
                }
            }
        }).execute();
    }


    @Override
    public void update(Todo todo, Callback<Todo> callback) {

    }

    @Override
    public void delete(Todo todo, Callback<Boolean> callback) {

    }

    @Override
    public void getAll(Callback<List<Todo>> callback) {

    }
}
