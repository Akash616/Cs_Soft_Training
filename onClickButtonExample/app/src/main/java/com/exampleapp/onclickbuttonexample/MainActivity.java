package com.exampleapp.onclickbuttonexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_one, btn_two, btn_three, btn_four;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_one = findViewById(R.id.btn_one);
        btn_two =  findViewById(R.id.btn_two);
        btn_three =  findViewById(R.id.btn_three);
        btn_four =  findViewById(R.id.btn_four);

        btn_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_one.setText("Button one clicked");
                btn_two.setText("Button 2");
            }
        });

        btn_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_two.setText("Button two clicked");
                btn_three.setText("Button 3");
            }
        });

        btn_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_three.setText("Button three clicked");
                btn_four.setText("Button 4");
            }
        });

        btn_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_four.setText("Button four clicked");
                Toast.makeText(getApplicationContext(), "Button 4 Clicked", Toast.LENGTH_SHORT).show();
                btn_one.setText("Button 1");
            }
        });

    }
}