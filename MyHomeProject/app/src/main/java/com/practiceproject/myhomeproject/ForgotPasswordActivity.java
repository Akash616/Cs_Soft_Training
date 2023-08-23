package com.practiceproject.myhomeproject;

import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;



public class ForgotPasswordActivity extends AppCompatActivity {

    //CountryCodePicker ccp;
    EditText editTextCarrierNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password_activity);

        //ccp = findViewById(R.id.ccp);
        editTextCarrierNumber = findViewById(R.id.editTextCarrierNumber);

        //ccp.registerCarrierNumberEditText(editTextCarrierNumber);

    }
}
