package com.itpropro.todo_app.presentation.presenter;

import com.itpropro.todo_app.domain.model.Todo;
import com.itpropro.todo_app.domain.usecase.impl.ToDoUseCaseImpl;
import com.itpropro.todo_app.domain.usecase.interfaces.ToDoUseCase;
import com.itpropro.todo_app.helpers.Callback;
import com.itpropro.todo_app.presentation.presenter.interfaces.ToDoListContract;
import com.itpropro.todo_app.repository.impl.ToDoLocalRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juank on 8/12/2017.
 */

public class ToDoListPresenter implements ToDoListContract.UserActionsListener {

    private ToDoListContract.View view;
    private ToDoUseCase toDoUseCase;
    private List<Todo> todoList;

    public ToDoListPresenter(ToDoListContract.View view) {
        this.view = view;
        this.toDoUseCase = new ToDoUseCaseImpl();
        this.todoList = new ArrayList<>(0);
    }

    @Override
    public void loadAll() {
        toDoUseCase.getAll(new Callback<List<Todo>>() {
            @Override
            public void success(List<Todo> result) {
                /*Se hace clear sobre la instancia del todoList para evitar que el adapter quede
                con una referencia vieja de los datos cuando se actualicen*/
                todoList.clear();
                todoList.addAll(result);
            }

            @Override
            public void error(Exception error) {
                //TODO MOSTRAR ERROR
                //view.showErrorMessage(error);
            }
        });
    }

    @Override
    public List<Todo> getToDosList() {
        return null;
    }
}
