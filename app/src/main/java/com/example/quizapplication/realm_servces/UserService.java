package com.example.quizapplication.realm_servces;

import com.example.quizapplication.realm_models.User;

import io.realm.Realm;

public class UserService {

    public double getUserScore(String login){
        Realm realm = Realm.getDefaultInstance();
        User user =  realm.where(User.class).equalTo("Login", login).findFirst();

        realm.beginTransaction();
        double _score = user.Points;

        realm.commitTransaction();
        return _score;
    }
}
