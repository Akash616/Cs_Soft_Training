package com.viewsapp.someviewexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnSignup; //Initialise - type is button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_page4);

        btnSignup = findViewById(R.id.btnSignup);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSignup.setText("Akash Gupta");
                //Toast.makeText(MainActivity.this, "My name is Akash Gupta", Toast.LENGTH_LONG).show();
                //Toast.makeText(getApplicationContext(),"My name is Akash gupta", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void btnClicked(View view) {
        Toast.makeText(getApplicationContext(), "Network Error...", Toast.LENGTH_SHORT).show();
    }

}