package com.uiapp.sharedpreferencesexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    EditText et_name, et_age;
    Button btn_click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        btn_click = findViewById(R.id.btn_click);

        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creating a shared pref object with a file name "MySharedPref" in private mode
                sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                // write all the data entered by the user in SharedPreference and commit
                editor.putString("Name", et_name.getText().toString());
                editor.putInt("Age", Integer.parseInt(et_age.getText().toString()));
                editor.commit();

                Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
            }
        });

    }
}