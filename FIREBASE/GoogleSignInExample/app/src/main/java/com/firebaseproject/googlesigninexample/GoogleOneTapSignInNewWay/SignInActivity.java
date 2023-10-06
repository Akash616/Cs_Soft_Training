package com.firebaseproject.googlesigninexample.GoogleOneTapSignInNewWay;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebaseproject.googlesigninexample.DemoTwoGeekForGeeks.MainActivity3;
import com.firebaseproject.googlesigninexample.DemoTwoGeekForGeeks.MainActivity4;
import com.firebaseproject.googlesigninexample.MainActivity;
import com.firebaseproject.googlesigninexample.R;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.BeginSignInResult;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class SignInActivity extends AppCompatActivity {

    SignInClient oneTapClient;
    BeginSignInRequest signInRequest;
    Button btnSignIn; //object for button
    private static final int REQ_ONE_TAP = 2;  // Can be any integer unique to the Activity.
    private boolean showOneTapUI = true;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //Initialize the button
        btnSignIn = findViewById(R.id.btnSignIn);

        oneTapClient = Identity.getSignInClient(this);
        signInRequest = BeginSignInRequest.builder()
                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        // Your server's client ID, not your Android client ID.
                        .setServerClientId(getString(R.string.web_client_id))
                        // Show all accounts on the device.
                        .setFilterByAuthorizedAccounts(true)
                        .build())
                // Automatically sign in when exactly one credential is retrieved.
                .setAutoSelectEnabled(true)
                .build();

        ActivityResultLauncher<IntentSenderRequest> activityResultLauncher =
                registerForActivityResult(new ActivityResultContracts.StartIntentSenderForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            try {
                                SignInCredential credential = oneTapClient.getSignInCredentialFromIntent(result.getData());
                                String idToken = credential.getGoogleIdToken();
                                //String username = credential.getId();
                                //String password = credential.getPassword();
                                if (idToken != null) {
                                    AuthCredential firebaseCredential = GoogleAuthProvider.getCredential(idToken, null);
                                    firebaseAuth.signInWithCredential(firebaseCredential)
                                            .addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                    if (task.isSuccessful()) {
                                                        // Sign in success, update UI with the signed-in user's information
                                                        Log.d("TAG", "signInWithCredential:success");
                                                        Toast.makeText(SignInActivity.this, "Sign in successfully", Toast.LENGTH_SHORT).show();
                                                        FirebaseUser user = firebaseAuth.getCurrentUser();
                                                    } else {
                                                        // If sign in fails, display a message to the user.
                                                        Log.w("TAG", "signInWithCredential:failure", task.getException());
                                                        Toast.makeText(SignInActivity.this, "Sign in failed", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                }
                            } catch (ApiException e) {
                                e.printStackTrace();
                                switch (e.getStatusCode()) {
                                    case CommonStatusCodes.CANCELED:
                                        Log.d("TAG", "One-tap dialog was closed.");
                                        Toast.makeText(SignInActivity.this, "One-tap dialog was closed.", Toast.LENGTH_SHORT).show();
                                        // Don't re-prompt the user.
                                        showOneTapUI = false;
                                        break;
                                    case CommonStatusCodes.NETWORK_ERROR:
                                        Log.d("TAG", "One-tap encountered a network error.");
                                        // Try again or just ignore.
                                        Toast.makeText(SignInActivity.this, "One-tap encountered a network error.", Toast.LENGTH_SHORT).show();
                                        break;
                                    default:
                                        Log.d("TAG", "Couldn't get credential from result."
                                                + e.getLocalizedMessage());
                                        Toast.makeText(SignInActivity.this, "Couldn't get credential from result", Toast.LENGTH_SHORT).show();
                                        break;
                                }
                            }
                        }
                    }
                });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oneTapClient.beginSignIn(signInRequest)
                        .addOnSuccessListener(SignInActivity.this, new OnSuccessListener<BeginSignInResult>() {
                            @Override
                            public void onSuccess(BeginSignInResult result) {
//                                try {
////                                    startIntentSenderForResult( //deprecated use - registerForActivityResult()
////                                            result.getPendingIntent().getIntentSender(), REQ_ONE_TAP,
////                                            null, 0, 0, 0);
//                                    IntentSenderRequest intentSenderRequest =
//                                            new IntentSenderRequest.Builder(result.getPendingIntent().getIntentSender()).build();
//                                    activityResultLauncher.launch(intentSenderRequest);
//                                } catch (IntentSender.SendIntentException e) {
//                                    Log.e("TAG", "Couldn't start One Tap UI: " + e.getLocalizedMessage());
//                                }
                                //                                    startIntentSenderForResult( //deprecated use - registerForActivityResult()
//                                            result.getPendingIntent().getIntentSender(), REQ_ONE_TAP,
//                                            null, 0, 0, 0);
                                //---we don't need try catch---------------------------
                                IntentSenderRequest intentSenderRequest =
                                        new IntentSenderRequest.Builder(result.getPendingIntent().getIntentSender()).build();
                                activityResultLauncher.launch(intentSenderRequest);
                            }
                        })
                        .addOnFailureListener(SignInActivity.this, new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // No saved credentials found.
                                Log.d("TAG", e.getLocalizedMessage());
                            }
                        });
            }
        });

    }

}