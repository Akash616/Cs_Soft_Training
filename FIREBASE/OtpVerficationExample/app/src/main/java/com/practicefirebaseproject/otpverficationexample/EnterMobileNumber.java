package com.practicefirebaseproject.otpverficationexample;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class EnterMobileNumber extends AppCompatActivity {

    EditText et_enterMobileNumber;
    Button btn_getOtp;
    ProgressBar pbar_sendingOtp;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entermobilenumber);

        btn_getOtp = findViewById(R.id.btn_getOtp);
        et_enterMobileNumber = findViewById(R.id.et_enterMobileNumber);
        pbar_sendingOtp = findViewById(R.id.pbar_sendingOtp);

        btn_getOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!et_enterMobileNumber.getText().toString().trim().isEmpty()) {
                    if ((et_enterMobileNumber.getText().toString().trim()).length() == 10) {

                        pbar_sendingOtp.setVisibility(View.VISIBLE);
                        btn_getOtp.setVisibility(View.INVISIBLE);
                        //-------------------Firebase----------------------------------------------
                        String phoneNumber = "+91" + et_enterMobileNumber.getText().toString();
                        sendVerificationCode(phoneNumber);

                    } else {
                        Toast.makeText(EnterMobileNumber.this, "Please enter correct number", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(EnterMobileNumber.this, "Enter Mobile number", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void sendVerificationCode(String number) {

        auth = FirebaseAuth.getInstance();
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(number)       // Phone number to verify
                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(this)                 // (optional) Activity for callback binding
                // If no activity is passed, reCAPTCHA verification can not be used.
                .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            pbar_sendingOtp.setVisibility(View.GONE);
            btn_getOtp.setVisibility(View.VISIBLE);
            Log.d(TAG, "onVerificationCompleted:" + phoneAuthCredential);
//            signInWithPhoneAuthCredential(credential);
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            pbar_sendingOtp.setVisibility(View.GONE);
            btn_getOtp.setVisibility(View.VISIBLE);
            Toast.makeText(EnterMobileNumber.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.w(TAG, "onVerificationFailed", e);
        }

        @Override
        public void onCodeSent(@NonNull String backEndOtp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(backEndOtp, forceResendingToken);
            pbar_sendingOtp.setVisibility(View.GONE);
            btn_getOtp.setVisibility(View.VISIBLE);
            Log.d(TAG, "onCodeSent:" + backEndOtp);
            Intent intent = new Intent(EnterMobileNumber.this, VerifyEnterOtp.class);
            intent.putExtra("mobile", et_enterMobileNumber.getText().toString());
            intent.putExtra("backEndOtp", backEndOtp);
            startActivity(intent);
        }
    };


}