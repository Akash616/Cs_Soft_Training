package com.viewbinding.viewbindingexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.viewbinding.viewbindingexample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainxml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainxml = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mainxml.getRoot();
        setContentView(view);

        mainxml.textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainxml.textview.setText("Akash Gupta!!!");
            }
        });

    }
}