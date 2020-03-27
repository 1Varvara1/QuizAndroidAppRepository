package com.example.quizapplication.activity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quizapplication.R;
import com.example.quizapplication.realm_models.User;
import com.example.quizapplication.realm_servces.AuthService;
import com.example.quizapplication.utilities.ActivityUtilities;
import com.google.android.material.snackbar.Snackbar;
import com.rengwuxian.materialedittext.MaterialEditText;

public class StartActivity extends AppCompatActivity {

    Button btnSignIn, btnRegister;
    AuthService authService;
    RelativeLayout root;
    TextView loginMessTextView;
    TextView registerMessTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_page);
        authService = new AuthService();
        btnSignIn = findViewById(R.id.btnSignIn);
        btnRegister = findViewById(R.id.btnRegister);
        root = findViewById(R.id.root_element);

    }

    public void btnRegisterClick(View view){

        LayoutInflater inflater =  LayoutInflater.from(this);
        View register_window= inflater.inflate(R.layout.register_window, null);


        final MaterialEditText email = register_window.findViewById(R.id.emailField);
        final MaterialEditText password = register_window.findViewById(R.id.passwordField);
        final MaterialEditText name = register_window.findViewById(R.id.nameField);

        registerMessTextView = register_window.findViewById(R.id.registerErrorField);
       // registerMessTextView.setText("");


        final AlertDialog dialog = new AlertDialog.Builder(this, R.style.AlertDialog)
                .setView(register_window)
                .setTitle("Register")
                .setMessage("Enter information for registration")
                .setPositiveButton("Submit", null)
                .setNegativeButton("Cancel", null)
                .create();



        dialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialogInterface) {

                Button button = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                button.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        String emailStr = email.getText().toString();
                        String passwordStr = password.getText().toString();
                        String loginStr = name.getText().toString();


                        if (TextUtils.isEmpty(emailStr)) {
                             registerMessTextView.setText("Enter your Email");
                        //    Snackbar.make(root, "Enter your Email", Snackbar.LENGTH_SHORT).show();
                            return;
                        }
                        else if (TextUtils.isEmpty(loginStr)) {
                            registerMessTextView.setText("Enter your Name");
                         //   Snackbar.make(root, "Enter your Name", Snackbar.LENGTH_SHORT).show();
                            return;
                        }
                        else if (passwordStr.length() < 4) {
                             registerMessTextView.setText("Enter your Password( more than 4 characters)");
                        //    Snackbar.make(root, "Enter your Password( more than 4 characters)", Snackbar.LENGTH_SHORT).show();
                            return;
                        }

                        else if(authService.isEmailTaken(emailStr)){
                            registerMessTextView.setText("User with such email is already exists");
                           // Snackbar.make(root, "Пользователь с таким email уже существует", Snackbar.LENGTH_SHORT).show();

                        }
                        else if(authService.isLoginTaken(loginStr)){
                              registerMessTextView.setText("User with such login is already exists");
                          //  Snackbar.make(root, "Пользователь с таким login уже существует", Snackbar.LENGTH_SHORT).show();

                        }
                        else{
                            authService.AddUser(loginStr,passwordStr,emailStr);
                            dialog.dismiss();
                            Snackbar.make(root, "User was added", Snackbar.LENGTH_SHORT).
                                    show();
                        }

                    }
                });


                Button buttonNegative = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_NEGATIVE);
                buttonNegative.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        // TODO Do something

                        //Dismiss once everything is OK.
                        dialog.dismiss();
                    }
                });
            }
        });
        dialog.show();
    }


   /* public void btnRegisterClick(View view){
        AlertDialog.Builder dialog =  new  AlertDialog.Builder( this,R.style.AlertDialog) ;
        dialog.setTitle("Регистрация");
        dialog.setMessage("Введите все данные для регистрации");



        LayoutInflater inflator =  LayoutInflater.from(this);
        View register_window= inflator.inflate(R.layout.register_window, null);
        dialog.setView(register_window);

        final MaterialEditText email = register_window.findViewById(R.id.emailField);
        final MaterialEditText password = register_window.findViewById(R.id.passwordField);
        final MaterialEditText name = register_window.findViewById(R.id.nameField);

        registerMessTextView = register_window.findViewById(R.id.registerErrorField);
        registerMessTextView.setText("");

        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });

        dialog.setPositiveButton("Submit", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                String emailStr = email.getText().toString();
                String passwordStr = password.getText().toString();
                String loginStr = name.getText().toString();


                if (TextUtils.isEmpty(emailStr)) {
                   // registerMessTextView.setText("Enter your Email");
                    Snackbar.make(root, "Enter your Email", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(loginStr)) {
                    //registerMessTextView.setText("Enter your Name");
                    Snackbar.make(root, "Enter your Name", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (passwordStr.length() < 4) {
                   // registerMessTextView.setText("Enter your Password( more than 4 characters)");
                    Snackbar.make(root, "Enter your Password( more than 4 characters)", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if(authService.isEmailTaken(emailStr)){
                    //registerMessTextView.setText("Пользователь с таким email уже существует");
                    Snackbar.make(root, "Пользователь с таким email уже существует", Snackbar.LENGTH_SHORT).show();

                }
                if(authService.isLoginTaken(loginStr)){
                  //  registerMessTextView.setText("Пользователь с таким login уже существует");
                    Snackbar.make(root, "Пользователь с таким login уже существует", Snackbar.LENGTH_SHORT).show();

                }
                else{
                    authService.AddUser(loginStr,passwordStr,emailStr);
                    Snackbar.make(root, "User was added", Snackbar.LENGTH_SHORT).
                            show();
                }

            }
        });
        dialog.show();
    }*/


    public void btnLogInClick(View view){
        LayoutInflater inflater =  LayoutInflater.from(this);
        View login_window= inflater.inflate(R.layout.login_window, null);


        final MaterialEditText login = login_window.findViewById(R.id.loginField);
        final MaterialEditText password = login_window.findViewById(R.id.passwordField);
        loginMessTextView = login_window.findViewById(R.id.loginErrorField);

        final AlertDialog dialog = new AlertDialog.Builder(this, R.style.AlertDialog)
                .setView(login_window)
                .setTitle("Sign in")
                .setMessage("Enter your login and password to sign in")
                .setPositiveButton("Submit", null) //Set to null. We override the onclick
                .setNegativeButton("Cancel", null)
                .create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialogInterface) {

                Button button = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                button.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        String loginStr = login.getText().toString();
                        String passwordStr = password.getText().toString();


                        if(TextUtils.isEmpty(loginStr))
                        {
                            loginMessTextView.setText("Enter Login");
                            //Snackbar.make(root, "Enter Login", Snackbar.LENGTH_SHORT).show();
                            return;
                        }

                        if(passwordStr.length()<4)
                        {
                            loginMessTextView.setText("Enter your Password(more 4 characters)");
                            //Snackbar.make(root, "Enter your Password(more 4 characters)", Snackbar.LENGTH_SHORT).show();
                            return;
                        }
                        User user = authService.isAccountExists( loginStr,passwordStr);
                        if(user==null){
                            loginMessTextView.setText("Your login or password is incorrect");

                            //   Snackbar.make(root,"Ошибка авторизации",Snackbar.LENGTH_SHORT).show();
                        }
                        else{
                            setSharedPreferences(user.getLogin(), user.getEmail(),user.getRole());
                            ActivityUtilities.getInstance().invokeNewActivity(StartActivity.this, MainActivity.class, true);
                        }
                    }
                });


                Button buttonNegative = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_NEGATIVE);
                buttonNegative.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        // TODO Do something

                        //Dismiss once everything is OK.
                        dialog.dismiss();
                    }
                });
            }
        });
        dialog.show();
    }


   /* public void btnLogInClick(View view){
        AlertDialog.Builder dialog =  new  AlertDialog.Builder(this, R.style.AlertDialog) ;
        dialog.setTitle("Вход");
        dialog.setMessage("Введите все данные для входа");

        LayoutInflater inflater =  LayoutInflater.from(this);
        View login_window= inflater.inflate(R.layout.login_window, null);
        dialog.setView(login_window);

        final MaterialEditText login = login_window.findViewById(R.id.loginField);
        final MaterialEditText password = login_window.findViewById(R.id.passwordField);
        loginMessTextView = login_window.findViewById(R.id.loginErrorField);
       // loginMessTextView.setText("");

        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();
            }
        });

        dialog.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

                String loginStr = login.getText().toString();
                String passwordStr = password.getText().toString();


                if(TextUtils.isEmpty(loginStr))
                {
                   // loginMessTextView.setText("Enter Login");
                    Snackbar.make(root, "Enter Login", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if(passwordStr.length()<4)
                {
                    //loginMessTextView.setText("Enter your Password(more 4 characters)");
                    Snackbar.make(root, "Enter your Password(more 4 characters)", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                User user = authService.isAccountExists( loginStr,passwordStr);
                if(user==null){
                    //loginMessTextView.setText("Your login or password is incorrect");

                    Snackbar.make(root,"Ошибка авторизации",Snackbar.LENGTH_SHORT).show();
                }
                else{
                    setSharedPreferences(user.getLogin(), user.getEmail(),user.getRole());
                    ActivityUtilities.getInstance().invokeNewActivity(StartActivity.this, MainActivity.class, true);
                }

            }});

        dialog.show();

    }*/


    public void setSharedPreferences(String login, String email, String role ){
        SharedPreferences sPref = getSharedPreferences("sPrefer",MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = sPref.edit();
        prefEditor.putString("login",login);
        prefEditor.putString("email",email);
        prefEditor.putString("role",role);
        prefEditor.putBoolean("is_auth",true);
        prefEditor.commit();
    }

    public void btnExitClick(View view) {
        finish();
        moveTaskToBack(true);
    }
}
