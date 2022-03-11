package com.example.tradingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


public class createacc extends AppCompatActivity {

    DatabaseHelper dbh = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createacc);
    }

    public void signinn(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    public void reg(View view)  {
        ProgressBar p = findViewById(R.id.progressBar);
        String name = (((EditText) findViewById(R.id.fname)).getText()).toString();
        String email = (((EditText) findViewById(R.id.emailid)).getText()).toString();
        String password = (((EditText) findViewById(R.id.password)).getText()).toString();
        String username = (((EditText) findViewById(R.id.username)).getText()).toString();
        Double phone =  Double.parseDouble((((EditText) findViewById(R.id.phone)).getText()).toString());                ;
        p.setVisibility(View.VISIBLE);

        boolean ck = dbh.InsertUserAcc(username,name,password,email,phone);
        if (ck = true)
            Toast.makeText(getApplicationContext(), "Data Inseted", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
    }
}