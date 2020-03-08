package com.example.quizapplication.activity;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizapplication.R;
import com.example.quizapplication.adapters.RatingAdapter;
import com.example.quizapplication.models.RatingTableModel;
import com.example.quizapplication.realm_servces.UserService;

import java.util.ArrayList;

public class RatingActivity extends AppCompatActivity {

    ListView listView;
    UserService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        listView = findViewById(R.id.listView);

        service = new UserService();

        initRecyclerView();
    }

    private void initRecyclerView(){

        ArrayList<RatingTableModel> data = service.getRatingInformation();
        RatingAdapter recyclerListAdapter = new RatingAdapter(this,R.id.rating_adapter_layout,data);
        listView.setAdapter(recyclerListAdapter);
    }

}
