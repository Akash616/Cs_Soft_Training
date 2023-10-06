package com.practiceproject.myhomeproject;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;

import java.util.ArrayList;
import java.util.Arrays;
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
    ImageView btSignIn, login_button;
    GoogleSignInClient googleSignInClient;
    FirebaseAuth firebaseAuth;
    CallbackManager mCallbackManager;
    FirebaseUser user;

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
                        et_signup_dob.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, date);
                datePickerDialog.show();
            }
        });

        firebaseAuthWithGoogle(); //email authen...

        //-----facebook login----------------------------
        FacebookSdk.sdkInitialize(getApplicationContext());
        login_button =  findViewById(R.id.login_button);
        firebaseAuthWithFacebook();

    }

    private void firebaseAuthWithFacebook() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken!=null && !accessToken.isExpired()){
            startActivity(new Intent(SignupActivity.this, HomeActivity.class));
        }

        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();

        // Initialize Facebook Login button
        mCallbackManager = CallbackManager.Factory.create();
        //LoginButton loginButton = findViewById(R.id.login_button); //bec. custom button
        login_button = findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions(SignupActivity.this, Arrays.asList("email", "public_profile"));
                LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.d(TAG, "facebook:onSuccess:" + loginResult);
                        handleFacebookAccessToken(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {
                        Log.d(TAG, "facebook:onCancel");
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Log.d(TAG, "facebook:onError", error);
                    }
                });
            }
        });
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            displayToast("Firebase authentication successful");
                            user = firebaseAuth.getCurrentUser();
                            if (user != null) {
//                                UsersData usersData = new UsersData();
//                                usersData.setUserName(user.getDisplayName());
                                Intent intent = new Intent(SignupActivity.this, HomeActivity.class);
//                                intent.putExtra("name", user.getDisplayName().toString());
//                                intent.putExtra("email", user.getEmail());
//                                intent.putExtra("image", user.getPhotoUrl());
                                startActivity(intent);
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            displayToast("Authentication Failed :" + task.getException().getMessage());
                            Toast.makeText(SignupActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private void firebaseAuthWithGoogle() {

        btSignIn = findViewById(R.id.bt_sign_in);

        //btSignIn.setSize(SignInButton.SIZE_ICON_ONLY);

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(SignupActivity.this, googleSignInOptions);

        btSignIn.setOnClickListener((View.OnClickListener) view -> {
            Intent intent = googleSignInClient.getSignInIntent();
            startActivityForResult(intent, 100);
        });
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            startActivity(new Intent(SignupActivity.this, HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }

    @Override
    protected void onStart() {
        super.onStart(); //if user is logged in then directly move to home screen
        user = firebaseAuth.getCurrentUser();
        if (user != null){
            Intent intent1 = new Intent(SignupActivity.this, HomeActivity.class);
            //intent1.putExtra("name", user.getDisplayName());
            startActivity(intent1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100) {
            Task<GoogleSignInAccount> signInAccountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
            if (signInAccountTask.isSuccessful()) {
                String s = "Google sign in successful";
                displayToast(s);
                try {
                    GoogleSignInAccount googleSignInAccount = signInAccountTask.getResult(ApiException.class);
                    if (googleSignInAccount != null) {
                        AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);
                        firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    displayToast("Firebase authentication successful");
                                    startActivity(new Intent(SignupActivity.this, HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                                } else {
                                    displayToast("Authentication Failed :" + task.getException().getMessage());
                                }
                            }
                        });
                    }
                } catch (ApiException e) {
                    e.printStackTrace();
                }
            }
        }

        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);

    }

    private void displayToast(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

    public void onClick(View view) {
        super.onBackPressed();
    }
}
