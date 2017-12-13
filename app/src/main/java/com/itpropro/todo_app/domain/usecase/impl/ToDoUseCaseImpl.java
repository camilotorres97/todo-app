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
                Long id = toDoRepository.insert(todo);
                todo.setId(id.intValue());
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
    public void update(final Todo todo, final Callback<Todo> callback) {
        new ThreadExecutor<Todo>(new ThreadExecutor.Task<Todo>() {
            @Override
            public Todo execute() throws Exception {
                toDoRepository.update(todo);
                return todo;
            }

            @Override
            public void finish(Exception error, Todo result) {
                if(error != null){
                    callback.error(error);
                }else{
                    callback.success(result);
                }
            }
        }).execute();
    }

    @Override
    public void delete(final Todo todo, final Callback<Boolean> callback) {
        new ThreadExecutor<Boolean>(new ThreadExecutor.Task<Boolean>() {
            @Override
            public Boolean execute() throws Exception {
                toDoRepository.delete(todo);
                return true;
            }

            @Override
            public void finish(Exception error, Boolean result) {
                if(error != null){
                    callback.error(error);
                }else{
                    callback.success(result);
                }
            }
        }).execute();
    }

    @Override
    public void getAll(final Callback<List<Todo>> callback) {
        new ThreadExecutor<List<Todo>>(new ThreadExecutor.Task<List<Todo>>() {

            @Override
            public List<Todo> execute() throws Exception {
                return toDoRepository.getAll();
            }

            @Override
            public void finish(Exception error, List<Todo> result) {
                if(error != null){
                    callback.error(error);
                }else{
                    callback.success(result);
                }
            }
        }).execute();
    }
}
