package com.exampleapp.onclickbuttonexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    EditText et_fname, et_lname, et_phone, et_email, et_address, et_password;
    Button btn_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        et_fname = findViewById(R.id.et_fname);
        et_lname = findViewById(R.id.et_lname);
        et_phone = findViewById(R.id.et_phone);
        et_email = findViewById(R.id.et_email);
        et_address = findViewById(R.id.et_address);
        et_password = findViewById(R.id.et_password);
        btn_signup = findViewById(R.id.btn_signup);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);

                //Bundle passing
                intent.putExtra("fname", et_fname.getText().toString());//(key,value)
                intent.putExtra("lname", et_lname.getText().toString());
                intent.putExtra("phone", et_phone.getText().toString());
                intent.putExtra("email", et_email.getText().toString());
                intent.putExtra("address", et_address.getText().toString());
                intent.putExtra("password", et_password.getText().toString());

                startActivity(intent);

                finish(); //finish this activity
            }
        });

    }
}