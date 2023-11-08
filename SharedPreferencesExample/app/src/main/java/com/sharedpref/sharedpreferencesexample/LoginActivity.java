package com.sharedpref.sharedpreferencesexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //code for verification - username and password

                SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                //now insert - insert karna ka leya Editor ko use karta hai.
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("flag", true);
                editor.apply();

                Intent iHome = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(iHome);

            }
        });

    }


}