package com.itpropro.todo_app.presentation.presenter;

import com.itpropro.todo_app.domain.model.Todo;
import com.itpropro.todo_app.domain.usecase.impl.ToDoUseCaseImpl;
import com.itpropro.todo_app.domain.usecase.interfaces.ToDoUseCase;
import com.itpropro.todo_app.helpers.Callback;
import com.itpropro.todo_app.presentation.presenter.interfaces.AddToDoContract;

import java.util.Date;

/**
 * Created by juank on 11/12/2017.
 */

public class AddToDoPresenter implements AddToDoContract.UserActionsListener {

    private AddToDoContract.View view;
    private ToDoUseCase toDoUseCase;

    public AddToDoPresenter(AddToDoContract.View view){
        this.view = view;
        this.toDoUseCase  = new ToDoUseCaseImpl();
    }

    @Override
    public void onSave(String description, boolean finished, Date finishDate, String image) {
        toDoUseCase.insert(description, finishDate, finished, image, 0, new Callback<Todo>() {
            @Override
            public void success(Todo result) {
                view.goToTodoListFragment();
            }

            @Override
            public void error(Exception error) {
                view.showErrorMessage(error);
            }
        });

    }
}
