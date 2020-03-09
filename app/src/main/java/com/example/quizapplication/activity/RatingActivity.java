package com.example.quizapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizapplication.R;
import com.example.quizapplication.adapters.RatingAdapter;
import com.example.quizapplication.models.RatingTableModel;
import com.example.quizapplication.realm_servces.UserService;

import java.util.ArrayList;
import java.util.Collections;

public class RatingActivity extends AppCompatActivity {

    ListView listView;
    UserService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        listView = findViewById(R.id.listView);

        service = new UserService();

        initRecyclerView(null);
    }

    private void initRecyclerView(String sort ){

        ArrayList<RatingTableModel> data = service.getRatingInformation();

        if (sort == "decrease"){

            decreaseSort(data);
        }
        else if (sort == "increase"){
            increaseSort(data);
        }

        RatingAdapter recyclerListAdapter = new RatingAdapter(this,R.layout.rating_adapter,data);
        listView.setAdapter(recyclerListAdapter);
    }

    private void  decreaseSort( ArrayList<RatingTableModel> data)
    {
        Collections.sort(data);
        Collections.reverse(data);
    }

    private void  increaseSort( ArrayList<RatingTableModel> data){
        Collections.sort(data);
    }

    public void sortDecrease(View view){
        initRecyclerView("decrease");
    }

    public void sortIncrease(View view){
        initRecyclerView("increase");
    }

    public void onBackClick(View view){
        startActivity(new Intent(RatingActivity.this , MainActivity.class));
    }
}
