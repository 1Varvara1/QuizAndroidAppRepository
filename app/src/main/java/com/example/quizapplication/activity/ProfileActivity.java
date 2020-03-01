package com.example.quizapplication.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.quizapplication.R;
import com.example.quizapplication.realm_servces.UserService;

public class ProfileActivity extends BaseActivity {

    UserService userService = new UserService();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        userService = new UserService();
        initToolbar(true);
        setToolbarTitle("User Profile");
        enableUpButton();


        SharedPreferences sPref= getSharedPreferences("sPrefer",MODE_PRIVATE);

        TextView loginF = (TextView)findViewById(R.id.loginField);
        loginF.setText("Your login:    "+sPref.getString("login",""));

        TextView emailF = (TextView)findViewById(R.id.emailField);
        emailF.setText("Your email:    "+sPref.getString("email",""));

        TextView scoreF = (TextView)findViewById(R.id.scoreField);
        String str = String.valueOf(userService.getUserScore(sPref.getString("login","")));
        scoreF.setText("Your score:    "+str.substring(0,3));


    }
}
