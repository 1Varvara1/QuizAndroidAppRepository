package com.example.quizapplication.realm_servces;

import android.app.Activity;

import com.example.quizapplication.realm_models.User;

import io.realm.Realm;

public class AuthService extends Activity {

    public User isAccountExists(String login, String password){
        Realm realm = Realm.getDefaultInstance();

             User user =  realm.where(User.class).equalTo("Login", login)
                .equalTo("Password", password).findFirst();


        if(user == null){
            return null;
        }
        else{

             realm.beginTransaction();
             String _login = user.Login;
             String _email = user.Email;
             String _role = user.Role;
             String _passw = user.Password;
             realm.commitTransaction();


            return new User(_login,_passw, _email, _role);
        }
    }

    public void getRoleOfUser(){

    }


/*    public void setSharedPreferences(String login, String email, String role ){
        SharedPreferences sPref = getSharedPreferences("sPrefer",MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = sPref.edit();
        prefEditor.putString("login",login);
        prefEditor.putString("email",email);
        prefEditor.putString("role",role);
        prefEditor.putBoolean("is_auth",true);
        prefEditor.commit();
    }
*/
    public void AddUser(String login, String password, String email){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        User userRealm =  realm.createObject(User.class);
        userRealm.setLogin(login);
        userRealm.setPassword(password);
        userRealm.setEmail(email);
        userRealm.setRole("user");

        realm.commitTransaction();
        realm.close();

    }

    public boolean isLoginTaken(String login){
        Realm realm = Realm.getDefaultInstance();

        User user =  realm.where(User.class).equalTo("Login", login).findFirst();


        realm.close();

        if(user == null){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean isEmailTaken(String email){
        Realm realm = Realm.getDefaultInstance();

        User user =  realm.where(User.class).equalTo("Email", email).findFirst();

        realm.close();

        if(user == null){
            return false;
        }
        else{
            return true;
        }
    }
}
