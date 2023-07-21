package com.sharedprefapp.sharedpreferencesexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    TextView tv_detailOne, tv_detailTwo, tv_detailThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        tv_detailOne = findViewById(R.id.tv_detailOne);
        tv_detailTwo = findViewById(R.id.tv_detailTwo);
        tv_detailThree = findViewById(R.id.tv_detailThree);

        //getting values from shared preferences.
        SharedPreferences sharedPreferences = getSharedPreferences("Demo", MODE_PRIVATE);
        tv_detailOne.setText(sharedPreferences.getString("Name","AKASH"));
        int age = sharedPreferences.getInt("Age",24);
        tv_detailTwo.setText(" "+age);
        tv_detailThree.setText(sharedPreferences.getString("Country","IND"));

    }
}