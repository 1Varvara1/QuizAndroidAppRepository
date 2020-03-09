package com.example.quizapplication.realm_servces;

import android.content.Context;

import com.example.quizapplication.Services.SharedPreferencesService;
import com.example.quizapplication.realm_models.TestPass;
import com.example.quizapplication.realm_models.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.Realm;

public  class MarkService {

    SharedPreferencesService prefService;
    public MarkService() {
        prefService = new SharedPreferencesService();
    }

    public void AddMarkForUser(double markToAdd, Context context, int wr, int right, double total){
        String login = prefService.getCurrentUserLogin(context);

        Realm realm = Realm.getDefaultInstance();

        User user =  realm.where(User.class).equalTo("Login", login).findFirst();

        realm.beginTransaction();
        user.Points += markToAdd;

        ///////////////

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        TestPass pass =  realm.createObject(TestPass.class);
        pass.setLogin(login);
        pass.setTestName(" ");
        pass.setRighAns(right);
        pass.setWrongAns(wr);
        pass.setPoints(total);
        pass.setDate(dateFormat.format(date));

        realm.commitTransaction();

        realm.close();

    }



}
