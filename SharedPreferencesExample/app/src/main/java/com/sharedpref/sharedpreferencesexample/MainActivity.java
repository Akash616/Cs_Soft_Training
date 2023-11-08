package com.sharedpref.sharedpreferencesexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override //mai ak kaam karana wala hu, jo muja kuch particular time ka baad kana hai.
            public void run() {
                //3 sec. ka baad kya run hoga yaha decide hoga.

                SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE); //sharedpref. create hoga login name say, agar usko sharedpref. nahi milta hai login name say
                Boolean check = sharedPreferences.getBoolean("flag", false); //for first time App open, defValue = false
                Intent iNext;
                if (check) { //for true (User is logged in)
                    iNext = new Intent(MainActivity.this, HomeActivity.class);
                } else { //for false (either first time or user is logged out)
                    iNext = new Intent(MainActivity.this, LoginActivity.class);
                }
                startActivity(iNext);
            }
        }, 3000);

    }


}