package com.practicefirebaseproject.otpverficationexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyEnterOtp extends AppCompatActivity {

    EditText et_inputOtp1, et_inputOtp2, et_inputOtp3, et_inputOtp4, et_inputOtp5, et_inputOtp6;
    TextView tv_mobileNumShow;
    Button btn_verifyOtp;
    String get_otpBackend;
    ProgressBar pbar_verifyOtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_enter_otp);

        et_inputOtp1 = findViewById(R.id.et_inputOtp1);
        et_inputOtp2 = findViewById(R.id.et_inputOtp2);
        et_inputOtp3 = findViewById(R.id.et_inputOtp3);
        et_inputOtp4 = findViewById(R.id.et_inputOtp4);
        et_inputOtp5 = findViewById(R.id.et_inputOtp5);
        et_inputOtp6 = findViewById(R.id.et_inputOtp6);
        pbar_verifyOtp = findViewById(R.id.pbar_verifyOtp);

        tv_mobileNumShow = findViewById(R.id.tv_mobileNumShow);
        tv_mobileNumShow.setText(String.format("+91-%s",
                getIntent().getStringExtra("mobile")
        ));

        get_otpBackend = getIntent().getStringExtra("backEndOtp");


        btn_verifyOtp = findViewById(R.id.btn_verifyOtp);
        btn_verifyOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!et_inputOtp1.getText().toString().trim().isEmpty() && !et_inputOtp2.getText().toString().trim().isEmpty() && !et_inputOtp3.getText().toString().trim().isEmpty() && !et_inputOtp4.getText().toString().trim().isEmpty() && !et_inputOtp5.getText().toString().trim().isEmpty() && !et_inputOtp6.getText().toString().trim().isEmpty()) {
                    //Toast.makeText(VerifyEnterOtp.this, "Otp verify", Toast.LENGTH_SHORT).show();

                    String enterCodeOtp = et_inputOtp1.getText().toString() +
                            et_inputOtp2.getText().toString() +
                            et_inputOtp3.getText().toString() +
                            et_inputOtp4.getText().toString() +
                            et_inputOtp5.getText().toString() +
                            et_inputOtp6.getText().toString();

                    if (get_otpBackend != null) {

                        pbar_verifyOtp.setVisibility(View.VISIBLE);
                        btn_verifyOtp.setVisibility(View.INVISIBLE);
                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(get_otpBackend, enterCodeOtp);
                        signInWithPhoneAuthCredential(phoneAuthCredential);

                    } else {
                        Toast.makeText(VerifyEnterOtp.this, "Please check internet connection", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(VerifyEnterOtp.this, "Please enter all number", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //Automatically cursor move to next
        numberOtpMove();

        //directly - if we are not using in future
        findViewById(R.id.tv_resendOtp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VerifyEnterOtp.this, "Resend otp clicked", Toast.LENGTH_SHORT).show();
                String phoneNumber = "+91" + getIntent().getStringExtra("mobile");
                resendVerificationCode(phoneNumber);
            }
        });

    }

    private void resendVerificationCode(String phoneNumber) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(phoneNumber)       // Phone number to verify
                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(this)
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(VerifyEnterOtp.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeSent(@NonNull String newBackendOtp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(newBackendOtp, forceResendingToken);
                        get_otpBackend = newBackendOtp;
                        Log.e("NewBackendOtp", "new otp" + get_otpBackend);
                        Toast.makeText(VerifyEnterOtp.this, "Otp send successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        pbar_verifyOtp.setVisibility(View.GONE);
                        btn_verifyOtp.setVisibility(View.VISIBLE);

                        if (task.isSuccessful()) {
                            Intent intent = new Intent(VerifyEnterOtp.this, Dashboard.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //1 WAY
                            startActivity(intent);
                            //finish(); //2 WAY
                        } else {
                            Toast.makeText(VerifyEnterOtp.this, "Enter the correct Otp", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    private void numberOtpMove() {

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

}