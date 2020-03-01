package com.example.quizapplication.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.quizapplication.R;
import com.example.quizapplication.constrants.AppConstants;
import com.example.quizapplication.utilities.ActivityUtilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SplashActivity extends AppCompatActivity {

    private ImageView imageView;
    private Animation animation;
    private ProgressBar progressBar;
    private ConstraintLayout layout;

    private static final int SPLASH_DURATION = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        layout = (ConstraintLayout) findViewById(R.id.splashLayout);
        imageView = (ImageView) findViewById(R.id.ivSplashIcon);
        animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.rotate);

        try {
            StartFileInit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void StartFileInit()throws IOException {


        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(getAssets().open(AppConstants.QUESTION_FILE)));
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
        String str_QuestionsFile = sb.toString();



        File path = this.getFilesDir();
        File file = new File( path,AppConstants.JSON_Question_set_fileName);
        boolean exists = file.exists();

        if(!exists){
            FileOutputStream stream = new FileOutputStream(file);
            try {
                stream.write(str_QuestionsFile.getBytes());
            } finally {
                stream.close();
            }
        }



        ///////////////////////////////////Reading file with categories//////////////////////////////////

        StringBuffer sb1 = new StringBuffer();
        BufferedReader br1 = null;
        try {
            br1 = new BufferedReader(new InputStreamReader(getAssets().open(AppConstants.CONTENT_FILE )));
            String temp;
            while ((temp = br1.readLine()) != null)
                sb1.append(temp);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br1.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String str_CategoryFile = sb1.toString();


        File path1 = this.getFilesDir();
        File file1 = new File( path1,AppConstants.JSON_Quiz_category_fileName);
        boolean exists1 = file1.exists();

        if(!exists1){
            FileOutputStream stream = new FileOutputStream(file1);
            try {
                stream.write(str_CategoryFile.getBytes());
            } finally {
                stream.close();
            }
        }



    }


    private void initFunctionality() {
        layout.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                imageView.startAnimation(animation);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        SharedPreferences sPref = getSharedPreferences("sPrefer",MODE_PRIVATE);
                        boolean isAuth = sPref.getBoolean("is_auth",false);
                        if (isAuth){
                            ActivityUtilities.getInstance().invokeNewActivity(SplashActivity.this, MainActivity.class, true);
                        }
                        else{
                            startActivity(new Intent(SplashActivity.this , StartActivity.class));
                        }

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        }, SPLASH_DURATION);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initFunctionality();
    }


   public void  TestJsonConvert() throws IOException {

   /*  Question[] questions = new Question[2];
      String [] s1= new String [] {"library","lang","man","school"};
      String [] s2= new String [] {"library","name","city","object"};

       questions[0] = new Question("what is c++?",s1,2,"Basics" );
       questions[1] = new Question("what is array?",s2,3,"c# Basics" );
       Questinarie q = new Questinarie();
       q.setQuestionnaires(questions);

       Gson gson = new Gson();

       String str= gson.toJson(q);*/
///////////////////////////////////Reading file with questions //////////////////////////////////

       StringBuffer sb = new StringBuffer();
       BufferedReader br = null;
       try {
           br = new BufferedReader(new InputStreamReader(getAssets().open(AppConstants.QUESTION_FILE)));
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
      String str_QuestionsFile = sb.toString();




       File path = this.getFilesDir();
       File file = new File( path,AppConstants.JSON_Question_set_fileName);
       boolean exists = file.exists();

       if(!exists){
           FileOutputStream stream = new FileOutputStream(file);
           try {
               stream.write(str_QuestionsFile.getBytes());
           } finally {
               stream.close();
           }
       }



       ///////////////////////////////////Reading file with questions //////////////////////////////////

       StringBuffer sb1 = new StringBuffer();
       BufferedReader br1 = null;
       try {
           br1 = new BufferedReader(new InputStreamReader(getAssets().open(AppConstants.CONTENT_FILE )));
           String temp;
           while ((temp = br1.readLine()) != null)
               sb1.append(temp);
       } catch (IOException e) {
           e.printStackTrace();
       } finally {
           try {
               br1.close();
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
       String str_CategoryFile = sb1.toString();


       File path1 = this.getFilesDir();
       File file1 = new File( path1,AppConstants.JSON_Quiz_category_fileName);
       boolean exists1 = file1.exists();

       if(!exists1){
           FileOutputStream stream = new FileOutputStream(file1);
           try {
               stream.write(str_CategoryFile.getBytes());
           } finally {
               stream.close();
           }
       }




      /* Questinarie q2 = new Questinarie();
       q2 = gson.fromJson(str1, Questinarie.class);*/

////////////////////////////////////////////////////////////////////////



    /*   String s3 =  gson.toJson(q2);
       String str16= String.valueOf(getAssets().open(AppConstants.QUESTION_FILE));
       try {
           FileWriter myWriter = new FileWriter(String.valueOf(getAssets().open(AppConstants.QUESTION_FILE)));
           myWriter.write(s3);

       } catch (IOException e) {
           System.out.println("An error occurred.");
           e.printStackTrace();
       }
       finally {
           try {

           } catch (Exception e) {
               e.printStackTrace();
           }
       }*/


      //int i =1;
   }
}
