package com.practiceproject.myhomeproject;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;


public class ForgotPasswordActivity extends AppCompatActivity {

    CountryCodePicker country_code_forgotPass;
    EditText et_forgotPass_number;
    FloatingActionButton fab_forgotPassword;
    ProgressBar pb_forgotPass;
    LinearLayout ll_forgotPass;
    Intent intent;
    FirebaseAuth auth;
    String phone_number;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password_activity);

        country_code_forgotPass = findViewById(R.id.country_code_forgotPass);
        et_forgotPass_number = findViewById(R.id.et_forgotPass_number);
        fab_forgotPassword = findViewById(R.id.fab_forgotPassword);
        ll_forgotPass = findViewById(R.id.ll_forgotPass);
        pb_forgotPass = findViewById(R.id.pb_forgotPass);

        fab_forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!et_forgotPass_number.getText().toString().trim().isEmpty()) {

                    String country_code = "+" + country_code_forgotPass.getSelectedCountryCode().trim();
                    phone_number = country_code + et_forgotPass_number.getText().toString().trim();
                    pb_forgotPass.setVisibility(View.VISIBLE);
                    ll_forgotPass.setVisibility(View.INVISIBLE);
                    //-------------Firebase----------------------------------------------
                    sendVerificationCode(phone_number);

                } else {
                    Toast.makeText(ForgotPasswordActivity.this, "Please enter mobile number", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void sendVerificationCode(String number) {
        auth = FirebaseAuth.getInstance();
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(auth)
                        .setPhoneNumber(number)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(mCallbacks)
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            pb_forgotPass.setVisibility(View.GONE);
            ll_forgotPass.setVisibility(View.VISIBLE);
            Log.d(TAG, "onVerificationCompleted:" + phoneAuthCredential);
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            pb_forgotPass.setVisibility(View.GONE);
            ll_forgotPass.setVisibility(View.VISIBLE);
            Toast.makeText(ForgotPasswordActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.w(TAG, "onVerificationFailed", e);
        }

        @Override
        public void onCodeSent(@NonNull String backEndOtp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(backEndOtp, forceResendingToken);
            pb_forgotPass.setVisibility(View.GONE);
            ll_forgotPass.setVisibility(View.VISIBLE);
            Log.d(TAG, "onCodeSent:" + backEndOtp);
            intent = new Intent(ForgotPasswordActivity.this, OtpVerificationActivity.class);
            intent.putExtra("phoneNumber", phone_number);
            intent.putExtra("backEndOtp", backEndOtp);
            startActivity(intent);
        }
    };

    public void onClick(View view) {
        super.onBackPressed();
    }
}
