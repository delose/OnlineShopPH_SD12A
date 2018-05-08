package com.delose.onlineshopph;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DELAY = 1000;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //INITIALIZE FIREBASE INSTANCE
        mAuth = FirebaseAuth.getInstance();

        //DRAW BEHIND STATUS BAR
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        mAuth.signOut();

        //CHECK IF THERE IS A SIGNED-IN USER
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //SIGNED IN USER
        if(currentUser!=null)
        {
            Toast.makeText(this, "A USER IS SIGNED IN", Toast.LENGTH_SHORT).show();
        }

        //NO SIGNED IN USER
        else
        {
            //NEXT ACTIVITY
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run()
                {
                    Intent intentLoginActivity;
                    intentLoginActivity = new Intent(SplashActivity.this,LoginActivity.class);

                    startActivity(intentLoginActivity);
                    finish();
                }
            },SPLASH_DELAY);
        }
    }
}
