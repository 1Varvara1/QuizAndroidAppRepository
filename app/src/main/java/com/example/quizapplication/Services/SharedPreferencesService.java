package com.example.quizapplication.Services;

import android.content.Context;
import android.content.SharedPreferences;

public  class SharedPreferencesService  {

    public String getCurrentUserLogin(Context context ){
        SharedPreferences sPref = context.getSharedPreferences("sPrefer",context.MODE_PRIVATE);
        String login = sPref.getString("login","");
        return login;
    }

 /*  public String getCurrentUserEmail(){
        SharedPreferences sPref = context.getSharedPreferences("sPrefer",MODE_PRIVATE);
        String email = sPref.getString("email","");
        return email;
    }

    public String getCurrentUserRole(){
        SharedPreferences sPref = getSharedPreferences("sPrefer",MODE_PRIVATE);
        String role = sPref.getString("role","");
        return role;
    }*/
}
