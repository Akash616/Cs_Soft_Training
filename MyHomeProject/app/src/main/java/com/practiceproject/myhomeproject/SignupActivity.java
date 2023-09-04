package com.practiceproject.myhomeproject;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hbb20.CountryCodePicker;

import java.util.ArrayList;
import java.util.Calendar;

public class SignupActivity extends AppCompatActivity {

    EditText et_signup_fName, et_signup_lName, et_signup_number, et_signup_email, et_signup_dob, et_signup_password, et_signup_confirmPass;
    CountryCodePicker country_code_signup;
    Spinner spinner_signup_gender;
    CheckBox cb_signup;
    FloatingActionButton fab_signup;
    ProgressBar pb_signup;
    LinearLayout ll_signup;
    ArrayList<String> arrayList;
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        et_signup_fName = findViewById(R.id.et_signup_fName);
        et_signup_lName = findViewById(R.id.et_signup_lName);
        et_signup_number = findViewById(R.id.et_signup_number);
        et_signup_email = findViewById(R.id.et_signup_email);
        et_signup_dob = findViewById(R.id.et_signup_dob);
        et_signup_password = findViewById(R.id.et_signup_password);
        et_signup_confirmPass = findViewById(R.id.et_signup_confirmPass);
        country_code_signup = findViewById(R.id.country_code_signup);
        spinner_signup_gender = findViewById(R.id.spinner_signup_gender);
        cb_signup = findViewById(R.id.cb_signup);
        fab_signup = findViewById(R.id.fab_signup);
        pb_signup = findViewById(R.id.pb_signup);
        ll_signup = findViewById(R.id.ll_signup);

        fab_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!et_signup_fName.getText().toString().trim().isEmpty() && !et_signup_lName.getText().toString().trim().isEmpty() && !et_signup_number.getText().toString().trim().isEmpty() && !et_signup_email.getText().toString().trim().isEmpty() &&
                        !et_signup_dob.getText().toString().trim().isEmpty() && !et_signup_password.getText().toString().trim().isEmpty() && !et_signup_confirmPass.getText().toString().trim().isEmpty()) {

                    if (spinner_signup_gender.getSelectedItem().toString().trim().equals("Gender")) {
                        Toast.makeText(SignupActivity.this, "Please select Gender", Toast.LENGTH_SHORT).show();
                    } else {
                        if (et_signup_password.getText().toString().equals(et_signup_confirmPass.getText().toString())) {
                            if (cb_signup.isChecked()) {
                                ll_signup.setVisibility(View.INVISIBLE);
                                pb_signup.setVisibility(View.VISIBLE);
                                intent = new Intent(SignupActivity.this, HomeActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); //1 way
                                startActivity(intent);
                                //finish();//2 way
                            } else {
                                Toast.makeText(SignupActivity.this, "Please agree terms and conditions", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(SignupActivity.this, "Password doesn't match", Toast.LENGTH_SHORT).show();
                        }
                    }

                } else {
                    Toast.makeText(SignupActivity.this, "Please enter all details", Toast.LENGTH_SHORT).show();
                }
            }
        });

        spinner_signup_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        arrayList = new ArrayList<>();
        arrayList.add("Gender");
        arrayList.add("Male");
        arrayList.add("Female");
        arrayList.add("Others");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, arrayList);
        //adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner_signup_gender.setAdapter(adapter);

        //DOB
        et_signup_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int date = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(SignupActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        et_signup_dob.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                }, year, month, date);
                datePickerDialog.show();
            }
        });

    }

    public void onClick(View view) {
        super.onBackPressed();
    }
}
