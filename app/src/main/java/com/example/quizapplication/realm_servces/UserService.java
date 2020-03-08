package com.example.quizapplication.realm_servces;

import com.example.quizapplication.models.RatingTableModel;
import com.example.quizapplication.realm_models.TestPass;
import com.example.quizapplication.realm_models.User;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class UserService {

    public double getUserScore(String login){
        Realm realm = Realm.getDefaultInstance();
        User user =  realm.where(User.class).equalTo("Login", login).findFirst();

        realm.beginTransaction();
        double _score = user.Points;

        realm.commitTransaction();
        return _score;
    }

    public ArrayList<RatingTableModel> getRatingInformation(){

        ArrayList<RatingTableModel> model = new  ArrayList<RatingTableModel>();
        Realm realm = Realm.getDefaultInstance();

        RealmResults<User> usersResult =  realm.where(User.class).findAll();

        for (User user: usersResult) {

            int countTest = 0;
            RealmResults<TestPass> pass = realm.where(TestPass.class).equalTo("Login",user.Login).findAll();
            for (TestPass p: pass) {
                countTest++;
            }

            model.add(new RatingTableModel(user.Login, user.Points, countTest ));

        }

       return model;
    }
}
