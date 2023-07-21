package com.sharedprefapp.sharedpreferencesexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText et_name, et_age, et_country;
    Button btn_one;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_name =  findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        et_country = findViewById(R.id.et_country);
        btn_one = findViewById(R.id.btn_one);

        btn_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences("Demo", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Name", et_name.getText().toString());
                //editor.putString("Age", et_age.getText().toString());
                editor.putInt("Age", Integer.parseInt(et_age.getText().toString()));
                editor.putString("Country", et_country.getText().toString());
                //editor.putBoolean("Boolean", false);
                editor.apply();
                //editor.commit();

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);

            }
        });

    }
}