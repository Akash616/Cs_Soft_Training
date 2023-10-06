package com.firebaseproject.googlesigninexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;

public class MainActivity2 extends AppCompatActivity {

    TextView tv_allDetails;
    Button sign_out_button;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv_allDetails = findViewById(R.id.tv_allDetails);

        String person_name = getIntent().getStringExtra("name");
        String person_given_name = getIntent().getStringExtra("person_given_name");
        String person_email = getIntent().getStringExtra("email");
        String person_id = getIntent().getStringExtra("person_id");

        tv_allDetails.setText("Person name : " + person_name
                + "Person Email : " + person_email
                + "Person Id : " + person_id
                + "Person given name : " + person_given_name);

        sign_out_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGoogleSignInClient.signOut();
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Logout successful", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}