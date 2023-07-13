package com.viewsapp.someviewexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnSignup; //Initialise - type is button
    EditText et_Fname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_page4);

        btnSignup = findViewById(R.id.btnSignup);
        et_Fname = findViewById(R.id.et_Fname);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //btnSignup.setText("Akash Gupta");
                //Toast.makeText(MainActivity.this, "My name is Akash Gupta", Toast.LENGTH_LONG).show();
                //Toast.makeText(getApplicationContext(),"My name is Akash gupta", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                //Bundle
                intent.putExtra("fname",et_Fname.getText().toString()); //Data Passing (key, value)

                startActivity(intent);
            }
        });

    }

    public void btnClicked(View view) {
        Toast.makeText(getApplicationContext(), "Network Error...", Toast.LENGTH_SHORT).show();
    }

}