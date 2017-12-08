package com.itpropro.todo_app.presentation.presenter.interfaces;

import com.itpropro.todo_app.domain.model.Todo;

import java.util.List;

/**
 * Created by juank on 8/12/2017.
 */

public interface ToDoListContract {

    interface View{

        void refreshToDos();

    }

    interface UserActionsListener{

        void loadAll();

        List<Todo> getToDosList();

    }
}
