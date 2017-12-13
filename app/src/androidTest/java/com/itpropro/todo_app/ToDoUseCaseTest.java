package com.itpropro.todo_app;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.itpropro.todo_app.database.AppDatabase;
import com.itpropro.todo_app.domain.model.Todo;
import com.itpropro.todo_app.domain.usecase.impl.ToDoUseCaseImpl;
import com.itpropro.todo_app.domain.usecase.interfaces.ToDoUseCase;
import com.itpropro.todo_app.helpers.Callback;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;

/**
 * Created by juank on 9/12/2017.
 */

@RunWith(AndroidJUnit4.class)
public class ToDoUseCaseTest {
    @Before
    public void initDb(){
        Context appContext = InstrumentationRegistry.getTargetContext();
        AppDatabase.init(appContext);
    }

    @After
    public void closeDb(){
        AppDatabase.getInstance().close();
    }

    public void insertTodo(){
        ToDoUseCase toDoUseCase = new ToDoUseCaseImpl();
        toDoUseCase.insert("Uno", new Date(), false, "http://aaa", 123, new Callback<Todo>() {
            @Override
            public void success(List<Todo> result) {
                Assert.assertThat(result.getId(), Matchers.notNullValue());
            }

            @Override
            public void error(Exception error) {
                Assert.assertThat(error, Matchers.notNullValue());
            }
        });
    }
}
