package com.uiapp.bundlesrielizable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

   // int age; //global variable
    TextView tv_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tv_detail = findViewById(R.id.tv_detail);

        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("Name"); //name - local variable
        //age = bundle.getInt("Age");
        String email = bundle.getString("Email");
        String password = bundle.getString("Password");

        tv_detail.setText("Name : "+name+"\nEmail : "+email+"\nPassword : "+password);
        //tv_detail.setText(bundle.getString("Name"));

    }
}