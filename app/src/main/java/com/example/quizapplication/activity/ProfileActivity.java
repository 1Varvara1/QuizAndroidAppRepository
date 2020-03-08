package com.example.quizapplication.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
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

        SharedPreferences sPref= getSharedPreferences("sPrefer",MODE_PRIVATE);

        TextView loginF = (TextView)findViewById(R.id.loginField);
        loginF.setText("Your login:    "+sPref.getString("login",""));

        TextView emailF = (TextView)findViewById(R.id.emailField);
        emailF.setText("Your email:    "+sPref.getString("email",""));

        TextView scoreF = (TextView)findViewById(R.id.scoreField);
        double score = userService.getUserScore(sPref.getString("login",""));
        scoreF.setText("Your score:    "+ String.format("%.2f", score));


    }

    public void onBackClick(View view){
        startActivity(new Intent(ProfileActivity.this , MainActivity.class));
    }
}
