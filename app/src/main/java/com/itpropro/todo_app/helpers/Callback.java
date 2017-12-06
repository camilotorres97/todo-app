package com.itpropro.todo_app.helpers;

import com.itpropro.todo_app.domain.model.User;

/**
 * Created by juank on 2/12/2017.
 */

public interface Callback<T> { //Tipo generico <>
    void success(T result);

    void error(Exception error);
}
