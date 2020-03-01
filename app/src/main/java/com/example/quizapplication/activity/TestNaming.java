package com.example.quizapplication.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizapplication.R;
import com.rengwuxian.materialedittext.MaterialEditText;

public class TestNaming extends AppCompatActivity {

    MaterialEditText name;
    TextView errorMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_naming);

        errorMsg = findViewById(R.id.errorLabel);
    }

    public void BackClick(View view){
        startActivity(new Intent(TestNaming.this , MainActivity.class));
    }
    public void onSetNameClick(View view){

        name = findViewById(R.id.testNameField);
        if(name.getText().toString().length()>0){
            SharedPreferences sPref = getSharedPreferences("sPrefer",MODE_PRIVATE);
            SharedPreferences.Editor prefEditor = sPref.edit();
            prefEditor.putString("testName",name.getText().toString());
            prefEditor.commit();

            try{
                startActivity(new Intent(TestNaming.this , AddTestActivity.class));
            }
            catch(Exception ex){
                Log.d("",ex.getMessage());
            }

        }
        else{
            errorMsg.setText("Please, enter test name");
        }


    }
}
