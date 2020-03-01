package com.example.quizapplication.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quizapplication.JsonModels.Category;
import com.example.quizapplication.JsonModels.CategoryItem;
import com.example.quizapplication.JsonModels.Questinarie;
import com.example.quizapplication.JsonModels.Question;
import com.example.quizapplication.JsonModels.TestAdder_CS_Basics;
import com.example.quizapplication.R;
import com.example.quizapplication.constrants.AppConstants;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class AddTestActivity extends AppCompatActivity {

    TextView countView ;
    TextView errorLabel;
    int counter;
    Question[] questions;
    String question_category;

    EditText question;
    EditText option1;
    EditText option2;
    EditText option3;
    EditText option4;
    Spinner rightAnswer;
Button addBtn;

    String  questionStr ;
    String option1Str ;
    String option2Str ;
    String option3Str ;
    String option4Str;
    String rightAnswerStr ;


    String str_QuestionsFile;
    Questinarie ConvertQuestionsResult;

    String  str_CategoryFile;
    CategoryItem ConvertCategoryResult;

    Category category;

    RelativeLayout root;

    private void StrsInit(){
         questionStr = question.getText().toString();
         option1Str = option1.getText().toString();
         option2Str = option2.getText().toString();
         option3Str = option3.getText().toString();
         option4Str = option4.getText().toString();
         rightAnswerStr =  rightAnswer.getSelectedItem().toString();
    }

    private String[] AnswersInit(){
        return new String[]{option1Str, option2Str,option3Str,option4Str};
    }

    private ArrayList<String> fieldsArrayInit(){
        ArrayList<String> fieldsArray = new ArrayList<String>(){
            {
                add(questionStr);
                add(option1Str);
                add(option2Str);
                add(option3Str);
                add(option4Str);
                add(rightAnswerStr);
            }
        };

        return fieldsArray;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_test);
        counter=0;
        questions = new Question[25];

        this.questionStr = "";
        this.option1Str = "";
        this.option2Str = "";
        this.option3Str = "";
        this.option4Str = "";
        this.rightAnswerStr = "";

        SharedPreferences sPref = getSharedPreferences("sPrefer",MODE_PRIVATE);
        int testCountJson = sPref.getInt("testCountJson",0);
        question_category = String.valueOf(testCountJson+1);

        countView =  findViewById(R.id.counterField);
        errorLabel = findViewById(R.id.counterField);


        question = findViewById(R.id.q);
        option1= findViewById(R.id.o1);
        option2= findViewById(R.id.o2);
        option3= findViewById(R.id.o3);
        option4= findViewById(R.id.o4);
        rightAnswer= findViewById(R.id.answerSp);
        addBtn=  findViewById(R.id.addBtn);
        root =  findViewById(R.id.rl);

        Spinner spinner = findViewById(R.id.answerSp);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.rightAnswer, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        SetCounterField();

        TestAdder_CS_Basics adder = new TestAdder_CS_Basics();
        ////////////////////////////////////////////////////////
       /* try {
            adder.AddTest(this.getFilesDir());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/
        ///////////////////////////////////////////////////////
    }

    private void SetCounterField(){

        String counterField =String.valueOf(counter+1);
        countView.setText("Question "+counterField+"/25");
    }

    public void AddQuestion(View view)  {

        StrsInit();
        if (!areFieldsValid(fieldsArrayInit())) return;
        Question question = new Question(questionStr, AnswersInit(),Integer.parseInt(rightAnswerStr)-1, question_category);

       //set question in the collection
        questions[counter] = question;
        // move to the new question
        counter++;

          if(counter<24){
              addBtn.setText("Add");
            RefreshActivity();
        }
        else if(counter==24){
            addBtn.setText("Finish");
            RefreshActivity();}
        else{

              boolean addResult = false;
              try {
                  addResult = addTest();
              } catch (IOException e) {
                  e.printStackTrace();
              }

              if(addResult){

                  Snackbar.make(root,"Added", Snackbar.LENGTH_SHORT).show();
              }
        }


    }

    boolean areFieldsValid(ArrayList<String> fieldsStr){
        return true;
    }


    private void RefreshActivity(){
        SetCounterField();

        question.setText("");
        option1.setText("");
        option2.setText("");
        option3.setText("");
        option4.setText("");
    }

    public void BackClick(View view){

        new SweetAlertDialog(AddTestActivity.this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Are you sure?")
                .setContentText("You wan`t be able to recover this!")
                .setCancelButton("Exit",new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        startActivity(new Intent(AddTestActivity.this , TestNaming.class));

                    }
                }).setConfirmButton("Cancel", new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sDialog) {
                sDialog.cancel();
            }
             })
                .show();
    }

    private boolean addTest() throws IOException {

        DeserializeQuestionsFile();
        ConvertStrToJsonQuestionFile();

        AddTestToQuestinarieModel();

        boolean resultQuestionAdding = SerializeQuestionsFile();
        addCategoryToFile();

        return true;
    }

    private void  DeserializeQuestionsFile() throws FileNotFoundException {


        File path = this.getFilesDir();

        File file = new File( path,AppConstants.JSON_Question_set_fileName);

        InputStream inputStream   = new FileInputStream(file.getPath());
        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(inputStream));
            String temp;
            while ((temp = br.readLine()) != null)
                sb.append(temp);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        str_QuestionsFile = sb.toString();
    }


    private void ConvertStrToJsonQuestionFile(){

        Gson gson = new Gson();
        ConvertQuestionsResult = gson.fromJson(str_QuestionsFile, Questinarie.class);

    }

    private void AddTestToQuestinarieModel(){

        for (Question q:questions ) {
            ConvertQuestionsResult.getQuestionnaires().add(q);
        }
    }

    private boolean SerializeQuestionsFile() throws IOException {

        Gson gson = new Gson();
        String strToWrite =  gson.toJson(ConvertQuestionsResult);

        File path = this.getFilesDir();
        File file = new File( path,AppConstants.JSON_Question_set_fileName);

            FileOutputStream stream = new FileOutputStream(file, false);
            try {
                stream.write(strToWrite.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                stream.close();
            }

      return true;

    }

    private boolean addCategoryToFile() throws IOException {

        category = new Category(question_category,  getCategoryName());
       // CategoryItem item = new CategoryItem();
        increase_SharedPreference_testCountJson_Field();


        DeserializeCategoryFile();
        ConvertStrToJsonCategoryFile();

        AddTestToCategoryItemModel();

        boolean resultCategoryAdding = SerializeCategoryFile();

        return false;
    }

    private void increase_SharedPreference_testCountJson_Field(){
        SharedPreferences sPref = getSharedPreferences("sPrefer",MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = sPref.edit();
        prefEditor.putInt("testCountJson",Integer.parseInt(question_category));
        prefEditor.commit();

    }
    private String getCategoryName(){

        SharedPreferences sPref = getSharedPreferences("sPrefer",MODE_PRIVATE);
        String result  = sPref.getString("testName","");
        return result ;

    }


    private void  DeserializeCategoryFile() throws FileNotFoundException {

        File path = this.getFilesDir();
        File file = new File( path,AppConstants.JSON_Quiz_category_fileName);

        InputStream inputStream   = new FileInputStream(file.getPath());

        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(inputStream ));
            String temp;
            while ((temp = br.readLine()) != null)
                sb.append(temp);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        str_CategoryFile = sb.toString();
    }
    private void ConvertStrToJsonCategoryFile(){

        Gson gson = new Gson();
        ConvertCategoryResult = gson.fromJson(str_CategoryFile, CategoryItem.class);

    }

    private void AddTestToCategoryItemModel(){

        ConvertCategoryResult.getItems().add(category);
    }

    private boolean SerializeCategoryFile() throws IOException {

        Gson gson = new Gson();
        String strToWrite =  gson.toJson(ConvertCategoryResult);

        File path = this.getFilesDir();
        File file = new File( path,AppConstants.JSON_Quiz_category_fileName);

        FileOutputStream stream = new FileOutputStream(file, false);
        try {
            stream.write(strToWrite.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            stream.close();
        }

        return true;

    }
}
