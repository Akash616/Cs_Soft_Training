package com.practiceproject.myhomeproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;


public class MainActivity2 extends AppCompatActivity {

//    CountryCodePicker ccp;
    EditText editTextCarrierNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editTextCarrierNumber = findViewById(R.id.editTextCarrierNumber);
//        ccp = findViewById(R.id.ccp);

        //Connecting editTextPhoneNumber with country code picker
//        ccp.registerCarrierNumberEditText(editTextCarrierNumber);

    }
}