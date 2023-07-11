package com.viewsapp.someviewexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_page4);

        btnSignup = findViewById(R.id.btnSignup);
        btnSignup.setText("Akash Gupta");

    }
}