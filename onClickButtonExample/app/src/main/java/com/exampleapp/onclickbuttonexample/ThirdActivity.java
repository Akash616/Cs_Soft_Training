package com.exampleapp.onclickbuttonexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {

    TextView txt_fname, txt_lname, txt_phone, txt_email, txt_address, txt_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Bundle bundle = getIntent().getExtras();
//        bundle.getString("fname");
//        bundle.getString("lname");
//        bundle.getString("phone");
//        bundle.getString("email");
//        bundle.getString("address");
//        bundle.getString("password");
        String firstName = bundle.getString("fname");
        String lastName = bundle.getString("lname");
        String phoneNumber = bundle.getString("phone");
        String userEmail = bundle.getString("email");
        String userAddress = bundle.getString("address");
        String userPassword = bundle.getString("password");

        txt_fname = findViewById(R.id.txt_fname);
        txt_lname = findViewById(R.id.txt_lname);
        txt_phone = findViewById(R.id.txt_phone);
        txt_email = findViewById(R.id.txt_email);
        txt_address = findViewById(R.id.txt_address);
        txt_password = findViewById(R.id.txt_password);

//        txt_fname.setText(bundle.getString("fname"));
//        txt_lname.setText(bundle.getString("lname"));
//        txt_phone.setText(bundle.getString("phone"));
//        txt_email.setText(bundle.getString("email"));
//        txt_address.setText(bundle.getString("address"));
//        txt_password.setText(bundle.getString("password"));
        txt_fname.setText("First Name : "+firstName);
        txt_lname.setText("Last Name : "+lastName);
        txt_phone.setText("Phone : "+phoneNumber);
        txt_email.setText("Email : "+userEmail);
        txt_address.setText("Address : "+userAddress);
        txt_password.setText("Password : "+userPassword);

    }
}