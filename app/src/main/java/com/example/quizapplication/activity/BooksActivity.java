package com.example.quizapplication.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapplication.R;
import com.example.quizapplication.adapters.BooksAdapter;
import com.example.quizapplication.realm_servces.BookServce;

import java.util.ArrayList;

public class BooksActivity extends BaseActivity {

    private ArrayList<String> mNames = new ArrayList<String>();
    private ArrayList<String> mImageUrls = new ArrayList<String>();
    BookServce bookServce;
    ImageView addBtn  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        HideAddOption();
        bookServce = new BookServce();


        findViewById(R.id.goBackIcon).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.addBookIcon).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(BooksActivity.this , AddBookActivity.class));
            }
        });
      //  initToolbar(true);
      //  setToolbarTitle(getString(R.string.quiz));
       // enableUpButton();

        initImageBitmaps();

    }

    private void HideAddOption(){
        SharedPreferences sPref = getSharedPreferences("sPrefer",MODE_PRIVATE);
        String role= sPref.getString("role","");
        addBtn = (ImageView)  findViewById(R.id.addBookIcon);
        if(role.equals("moderator")){
            addBtn.setVisibility(View.VISIBLE);
        }
        else{
            addBtn.setVisibility(View.GONE);
        }
    }

    private void initImageBitmaps(){

      /*  ArrayList<Book> books = bookServce.getBooks();
        mImageUrls.add("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg");
        mNames.add("Havasu Falls");

        mImageUrls.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        mNames.add("Trondheim");

        mImageUrls.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mNames.add("Portugal");

        mImageUrls.add("https://i.redd.it/j6myfqglup501.jpg");
        mNames.add("Rocky Mountain National Park");


        mImageUrls.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        mNames.add("Mahahual");

        mImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg");
        mNames.add("Frozen Lake");


        mImageUrls.add("https://i.redd.it/glin0nwndo501.jpg");
        mNames.add("White Sands Desert");

        mImageUrls.add("https://i.redd.it/obx4zydshg601.jpg");
        mNames.add("Austrailia");

        mImageUrls.add("https://i.imgur.com/ZcLLrkY.jpg");
        mNames.add("Washington");*/

        initRecyclerView();
    }


    private void initRecyclerView(){

        mNames=  bookServce.getBooksTitles();
        mImageUrls = bookServce.getBooksUrls();

        RecyclerView recyclerView = findViewById(R.id.recycleBooksView);
        BooksAdapter adapter = new BooksAdapter( mNames, mImageUrls,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(BooksActivity.this , MainActivity.class));
    }

    public void toMain(){
        finish();
       // startActivity(new Intent(BooksActivity.this , MainActivity.class));
    }
}
