package com.example.quizapplication.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.quizapplication.R;
import com.example.quizapplication.realm_models.Book;
import com.example.quizapplication.realm_servces.BookServce;

public class AddBookActivity extends AppCompatActivity {

    ImageView addBtn ;
    TextView errorMsg;
    EditText titleEdit, authEdit, imgEdit;
    Spinner themeSp;
    BookServce bookServce;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        bookServce = new BookServce();

        findViewById(R.id.addBookIcon).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onAddClick();
            }
        });

      /*  findViewById(R.id.filterBookIcon).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onFilterClick();
            }
        });*/

        findViewById(R.id.goBackAddingIcon).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.themesSpinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.themes_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }


    public void onFilterClick(){

    }


    public void onAddClick(){
        errorMsg = findViewById(R.id.errorLabel);
        titleEdit =findViewById(R.id.nameEdit);
        authEdit = findViewById(R.id.authEdit);
        imgEdit=findViewById(R.id.imageEdit);
        themeSp = findViewById(R.id.themesSpinner);

        if(titleEdit.getText().toString().length()==0 ||
                authEdit.getText().toString().length()==0 ||
                imgEdit.getText().toString().length()==0
        ){
            errorMsg.setText("Fill all the fields");
        }
        else{
            Book book = new Book (themeSp.getSelectedItem().toString(), titleEdit.getText().toString(),
                    authEdit.getText().toString(), imgEdit.getText().toString());
            bookServce.AddBook(book);
            startActivity(new Intent(AddBookActivity.this , BooksActivity.class));
        }

    }

    public void prewOpen(View view) {

        AlertDialog.Builder dialog =  new  AlertDialog.Builder( this,R.style.AlertDialog) ;
        dialog.setTitle("Preview");

        LayoutInflater inflator =  LayoutInflater.from(this);
        View prew_window= inflator.inflate(R.layout.image_preview, null);
        dialog.setView(prew_window);

        ImageView imageView = prew_window.findViewById(R.id.imgPrew);
        EditText editUrl = (EditText)findViewById(R.id.imageEdit);
        String imgUrl   = editUrl.getText().toString().trim();

        Glide.with( prew_window).load(imgUrl).placeholder(R.drawable.bookb).into(imageView);

        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });

        dialog.show().getWindow().setLayout(1000, 1200);
    }
}
