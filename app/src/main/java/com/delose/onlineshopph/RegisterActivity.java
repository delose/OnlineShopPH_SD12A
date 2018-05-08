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
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private Button buttonRegister;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextConfirmPassword;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPassword;

    private static final String TAG = "RegisterActivity";
    private static final int PASSWORD_LENGTH = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //GET FIREBASEAUTH INSTANCE
        mAuth = FirebaseAuth.getInstance();

        //DRAW BEHIND STATUS BAR
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        //GET VIEWS
        buttonRegister = findViewById(R.id.button_register_register);
        textInputEditTextEmail = findViewById(R.id.text_email_register);
        textInputEditTextPassword = findViewById(R.id.text_password_register);
        textInputEditTextConfirmPassword = findViewById(R.id.text_confpassword_register);
        textInputLayoutEmail = findViewById(R.id.text_layout_email_register);
        textInputLayoutPassword = findViewById(R.id.text_layout_password_register);
        textInputLayoutConfirmPassword = findViewById(R.id.text_layout_confpassword_register);

        //WHEN REGISTER BUTTON IS CLICKED
        buttonRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                    register();
            }
        });
    }

    private void register()
    {
        String email = textInputEditTextEmail.getText().toString();
        String password = textInputEditTextPassword.getText().toString();
        String confirmPassword = textInputEditTextConfirmPassword.getText().toString();

        textInputLayoutEmail.setErrorEnabled(false);
        textInputLayoutPassword.setErrorEnabled(false);
        textInputLayoutConfirmPassword.setErrorEnabled(false);

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

        //IF PASSWORD TOO SHORT
        else if(password.length()<PASSWORD_LENGTH)
        {
            textInputLayoutPassword.setErrorEnabled(true);
            textInputLayoutPassword.setError(getString(R.string.invalid_password));
            return;
        }

        //IF CONFIRM PASSWORD IS EMPTY
        if(confirmPassword.isEmpty())
        {
            textInputLayoutConfirmPassword.setErrorEnabled(true);
            textInputLayoutConfirmPassword.setError(getString(R.string.missing_confirm_password));
            return;
        }

        //IF CONFIRM PASSWORD DOESN'T MATCH
        else if(!confirmPassword.equals(password))
        {
            textInputLayoutConfirmPassword.setErrorEnabled(true);
            textInputLayoutConfirmPassword.setError(getString(R.string.invalid_confirm_password));
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                //REGISTER SUCCESSFUL; AUTOMATICALLY LOGS IN
                if (task.isSuccessful())
                {
                    Log.d(TAG, "createUserWithEmail:success");
                    Toast.makeText(RegisterActivity.this, "ACCOUNT REGISTRATION SUCCESSFUL", Toast.LENGTH_SHORT).show();

                    //BRING USER TO MAIN ACTIVITY
                    Intent intentMain = new Intent(RegisterActivity.this,MainActivity.class);
                    startActivity(intentMain);
                }

                //REGISTER FAILURE
                else
                {
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                }
            }
        });

    }
}
