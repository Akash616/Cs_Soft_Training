package com.uiapp.bundlesrielizable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btn_click;
    EditText et_name, et_age, et_email, et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_click = findViewById(R.id.btn_click);
        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);

        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // creating a intent
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                // creating a bundle object
                Bundle bundle = new Bundle();
                // storing the string value in the bundle
                // which is mapped to key
                bundle.putString("Name", et_name.getText().toString());
                //bundle.putInt("Age", Integer.parseInt(et_age.getText().toString()));
                bundle.putString("Email", et_email.getText().toString());
                bundle.putString("Password", et_password.getText().toString());

                // passing the bundle into the intent
                intent.putExtras(bundle);

                // starting the intent
                startActivity(intent);
            }
        });

    }
}