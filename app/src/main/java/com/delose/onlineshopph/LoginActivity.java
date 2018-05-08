package com.delose.onlineshopph;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private Button buttonLogin;
    private Button buttonRegister;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;

    private FirebaseAuth mAuth;

    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        //DRAW BEHIND STATUS BAR
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        //GET VIEWS
        buttonLogin = findViewById(R.id.button_login_login);
        buttonRegister = findViewById(R.id.button_register_login);
        textInputEditTextEmail = findViewById(R.id.text_email_login);
        textInputEditTextPassword = findViewById(R.id.text_password_login);
        textInputLayoutEmail = findViewById(R.id.text_layout_email_login);
        textInputLayoutPassword = findViewById(R.id.text_layout_password_login);

        //WHEN LOGIN IS CLICKED
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        //WHEN CREATE ACCOUNT (REGISTER) IS CLICKED
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //START REGISTER ACTIVITY
                Intent intentRegister = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intentRegister);
            }
        });
    }

    private void login()
    {
        String email = textInputEditTextEmail.getText().toString().trim();
        String password = textInputEditTextPassword.getText().toString().trim();

        //IF EMAIL IS EMPTY
        if(email.isEmpty())
        {
            textInputLayoutEmail.setErrorEnabled(true);
            textInputLayoutEmail.setError(getString(R.string.missing_email));
            return;
        }

        //IF EMAIL ISN'T VALID
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            textInputLayoutEmail.setErrorEnabled(true);
            textInputLayoutEmail.setError(getString(R.string.invalid_email));
            return;
        }

        //IF PASSWORD IS EMPTY
        if(password.isEmpty())
        {
            textInputLayoutPassword.setErrorEnabled(true);
            textInputLayoutPassword.setError(getString(R.string.missing_email));
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                //SIGN IN SUCCESSFUL
                if(task.isSuccessful())
                {
                    Log.d(TAG,"Login Successful");

                    Intent intentMain = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intentMain);

                }

                //SIGN IN FAILURE
                if(!task.isSuccessful())
                {
                    Log.w(TAG,"Login Failure",task.getException());
                    Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}
