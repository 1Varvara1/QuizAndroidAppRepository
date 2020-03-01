package com.example.quizapplication.realm_models;

import io.realm.RealmObject;

public class User extends RealmObject {
    public  String Login;
    public String Password;
    public String Email;
    public  String Role;

    public double getPoints() {
        return Points;
    }

    public void setPoints(double points) {
        Points = points;
    }

    public double Points;

    public User(){}

    public User(  String login,String password, String email){
        Login=login;
        Password = password;
        Email = email;

    }

    public User(  String login,String password, String email, String role){
        Login=login;
        Password = password;
        Email = email;
        Role= role;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }
}
