package com.itpropro.todo_app.helpers;

/**
 * Created by juank on 9/12/2017.
 */

//REFERENCIAS: http://square.github.io/retrofit/
public class RetrofitSingleton {
    private static Retrofit retrofit;

    public static Retrofit getInstance(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl('https://todo-app-e0980.firebaseio.com/')
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
    }
}
