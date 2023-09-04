package com.practiceproject.myhomeproject;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OtpVerificationActivity extends AppCompatActivity {

    EditText et_inputOtp1, et_inputOtp2, et_inputOtp3, et_inputOtp4, et_inputOtp5, et_inputOtp6;
    ProgressBar pb_otpVerify;
    LinearLayout ll_otpVerify;
    Intent intent;
    FloatingActionButton fab_verifyOtp;
    Button btn_resendOtp;
    String get_otpBackend;
    FirebaseAuth auth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_verification_activity);

        fab_verifyOtp = findViewById(R.id.fab_verifyOtp);
        et_inputOtp1 = findViewById(R.id.et_inputOtp1);
        et_inputOtp2 = findViewById(R.id.et_inputOtp2);
        et_inputOtp3 = findViewById(R.id.et_inputOtp3);
        et_inputOtp4 = findViewById(R.id.et_inputOtp4);
        et_inputOtp5 = findViewById(R.id.et_inputOtp5);
        et_inputOtp6 = findViewById(R.id.et_inputOtp6);
        ll_otpVerify = findViewById(R.id.ll_otpVerify);
        pb_otpVerify = findViewById(R.id.pb_otpVerify);
        btn_resendOtp = findViewById(R.id.btn_resendOtp);

        get_otpBackend = getIntent().getStringExtra("backEndOtp");

        fab_verifyOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!et_inputOtp1.getText().toString().trim().isEmpty() && !et_inputOtp2.getText().toString().trim().isEmpty() && !et_inputOtp3.getText().toString().trim().isEmpty() &&
                        !et_inputOtp4.getText().toString().trim().isEmpty() && !et_inputOtp5.getText().toString().trim().isEmpty() && !et_inputOtp6.getText().toString().trim().isEmpty()) {

                    String userEnteredCodeOtp = et_inputOtp1.getText().toString() +
                            et_inputOtp2.getText().toString() +
                            et_inputOtp3.getText().toString() +
                            et_inputOtp4.getText().toString() +
                            et_inputOtp5.getText().toString() +
                            et_inputOtp6.getText().toString();

                    if (get_otpBackend != null) {
                        pb_otpVerify.setVisibility(View.VISIBLE);
                        ll_otpVerify.setVisibility(View.INVISIBLE);
                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(get_otpBackend, userEnteredCodeOtp);
                        signInWithPhoneAuthCredential(phoneAuthCredential);
                    } else {
                        Toast.makeText(OtpVerificationActivity.this, "otp is null", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(OtpVerificationActivity.this, "Please enter all number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Automatically cursor move to next
        autoCursorOtpMove();

        //directly - if we are not using in future
        findViewById(R.id.btn_resendOtp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OtpVerificationActivity.this, "Resend btn clicked", Toast.LENGTH_SHORT).show();
                String phoneNumber = getIntent().getStringExtra("phoneNumber");
                resendVerificationCode(phoneNumber);
            }
        });

    }

    private void resendVerificationCode(String phoneNumber) {
        auth = FirebaseAuth.getInstance();
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(auth)
                        .setPhoneNumber(phoneNumber)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(OtpVerificationActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String newBackendOtp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(newBackendOtp, forceResendingToken);
                                get_otpBackend = newBackendOtp;
                                Log.e("NewBackendOtp", "new otp" + get_otpBackend);
                                Toast.makeText(OtpVerificationActivity.this, "Otp send successfully", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void autoCursorOtpMove() {
        et_inputOtp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    et_inputOtp2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_inputOtp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    et_inputOtp3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_inputOtp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    et_inputOtp4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_inputOtp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    et_inputOtp5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_inputOtp5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    et_inputOtp6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential phoneAuthCredential) {
        auth = FirebaseAuth.getInstance();
        auth.signInWithCredential(phoneAuthCredential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        pb_otpVerify.setVisibility(View.GONE);
                        ll_otpVerify.setVisibility(View.VISIBLE);
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential:success");
                            intent = new Intent(OtpVerificationActivity.this, HomeActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //1 WAY
                            startActivity(intent);
                            //finish(); //2 WAY
                        } else {
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(OtpVerificationActivity.this, "Enter the correct Otp", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    public void onClick(View view) {
        super.onBackPressed();
    }
}
