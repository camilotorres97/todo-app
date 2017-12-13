package com.itpropro.todo_app.helpers;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by juank on 8/12/2017.
 */

public class ThreadExecutor<T> {
    private Task<T> task;

    public ThreadExecutor(Task<T> task){
        this.task = task;
    }

    public void execute(){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<T> future = executor.submit(new Callable<T>(){
           @Override
            public T call() throws Exception{
               return task.execute();
           }
        });

        try{
            T result = future.get();
            if(result != null && future.isDone()){
                task.finish(null, result);
            }else{
                task.finish(null, null);
            }
        }catch (InterruptedException e){
            task.finish(e, null);
        }catch (ExecutionException e){
            task.finish(e, null);
        }
    }

    public interface Task<T>{
        //Lo que se ejecutará en segundo plano
        T execute() throws Exception;
        //Que se hará cuando se finalice la ejecución en segundo plano
        void finish(Exception error, T result);
    }
}
