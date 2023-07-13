package com.viewsapp.someviewexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    Button btn_secondScreen;
    TextView txt_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //Bundle
        Bundle bundle = getIntent().getExtras();
        bundle.getString("fname");

        btn_secondScreen = findViewById(R.id.btn_secondScreen);
        txt_name = findViewById(R.id.txt_name);

        //String name = bundle.getString("fname");
        //txt_name.setText("First Name : "+name); //1st way
        txt_name.setText(bundle.getString("fname")); //2nd way

        btn_secondScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });

    }
}