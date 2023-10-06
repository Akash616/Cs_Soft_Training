package com.firebaseproject.facebookloginauthexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {

    TextView tv_name;
    ImageView iv_profileImg;
    Button btn_logout;
    FirebaseAuth mAuth;
    CallbackManager mCallbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv_name = findViewById(R.id.tv_name);
        iv_profileImg = findViewById(R.id.iv_profileImg);

        String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");
        String urlImg = getIntent().getStringExtra("image");
        tv_name.setText(name+"\n"+email);

        Glide.with(MainActivity2.this).load(urlImg).placeholder(R.drawable.person).into(iv_profileImg);

        btn_logout = findViewById(R.id.btn_logout);
        mAuth = FirebaseAuth.getInstance();
        mCallbackManager = CallbackManager.Factory.create(); //LoginManager.getInstance().logOut();
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                LoginManager.getInstance().logOut();
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

    }
}