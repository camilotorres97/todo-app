package com.itpropro.todo_app.helpers;

import com.itpropro.todo_app.domain.model.Todo;

import java.util.List;

/**
 * Created by juank on 2/12/2017.
 */

public interface Callback<T> { //Tipo generico <>
    void success(T result);

    void error(Exception error);
}
