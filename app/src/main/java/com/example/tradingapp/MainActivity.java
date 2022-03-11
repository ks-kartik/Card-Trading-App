package com.example.tradingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View v){
        String username = (((EditText) findViewById(R.id.username)).getText()).toString();
        String password = (((EditText) findViewById(R.id.password)).getText()).toString();
        DatabaseHelper db = new DatabaseHelper(this);
        if(db.AuthenticateUser(username,password)) {
            Toast.makeText(getApplicationContext(), "Login Success!", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this,Dashboard.class);
            i.putExtra("Username", username);
            startActivity(i);
        }
        else
            Toast.makeText(getApplicationContext(), "Incorrect Username or Password\n Please Retry!!!", Toast.LENGTH_SHORT).show();

    }
    public void signupp(View v){
        Intent i = new Intent(this, createacc.class);
        startActivity(i, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            finishAndRemoveTask();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}