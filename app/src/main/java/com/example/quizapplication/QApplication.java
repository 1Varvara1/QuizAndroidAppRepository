package com.example.quizapplication;

import android.app.Application;

import io.realm.Realm;

public class QApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
