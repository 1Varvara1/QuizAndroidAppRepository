package com.example.quizapplication.realm_models;

import io.realm.RealmObject;

public class TestPass extends RealmObject {
    public  String Login;
    public String  TestName;
    public double Points ;
    public int WrongAns;
    public int RighAns;
    public int TotalAns;
    public String Date;

    public String getLogin() {
        return Login;
    }

    public String getTestName() {
        return TestName;
    }

    public void setTestName(String testName) {
        TestName = testName;
    }

    public double getPoints() {
        return Points;
    }

    public void setPoints(double points) {
        Points = points;
    }

    public int getWrongAns() {
        return WrongAns;
    }

    public void setWrongAns(int wrongAns) {
        WrongAns = wrongAns;
    }

    public int getRighAns() {
        return RighAns;
    }

    public void setRighAns(int righAns) {
        RighAns = righAns;
    }

    public int getTotalAns() {
        return TotalAns;
    }

    public void setTotalAns(int totalAns) {
        TotalAns = totalAns;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setLogin(String login) {
        Login = login;
    }
}
