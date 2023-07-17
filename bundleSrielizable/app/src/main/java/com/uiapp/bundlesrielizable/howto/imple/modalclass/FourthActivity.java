package com.uiapp.bundlesrielizable.howto.imple.modalclass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.uiapp.bundlesrielizable.R;

public class FourthActivity extends AppCompatActivity {

    int age;
    TextView tv_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        tv_detail = findViewById(R.id.tv_detail);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String name = bundle.getString("Name");
            age = bundle.getInt("Age");
            String email = bundle.getString("Email");
            String password = bundle.getString("Password");

            tv_detail.setText("Name : " + name + "\nAge : " + age + "\nEmail : " + email + "\nPassword : " + password);
            //tv_detail.setText(bundle.getString("Name"));

        } else {
            Toast.makeText(this, "Empty Details...", Toast.LENGTH_SHORT).show();
        }

    }
}