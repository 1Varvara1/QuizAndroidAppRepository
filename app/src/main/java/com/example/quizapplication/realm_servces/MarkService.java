package com.example.quizapplication.realm_servces;

import android.content.Context;

import com.example.quizapplication.Services.SharedPreferencesService;
import com.example.quizapplication.realm_models.User;

import io.realm.Realm;

public  class MarkService {

    SharedPreferencesService prefService;
    public MarkService() {
        prefService = new SharedPreferencesService();
    }

    public void AddMarkForUser(double markToAdd, Context context){
        String login =prefService.getCurrentUserLogin(context);

        Realm realm = Realm.getDefaultInstance();

        User user =  realm.where(User.class).equalTo("Login", login).findFirst();

        realm.beginTransaction();
        user.Points+=markToAdd;
        realm.commitTransaction();

        realm.close();

    }

}
