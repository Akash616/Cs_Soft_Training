package com.practiceproject.myhomeproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.practiceproject.myhomeproject.modalClass.RoomsModal;

public class HomeDetails extends AppCompatActivity {

    ImageView iv_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_details);

        iv_home = findViewById(R.id.iv_home);

        RoomsModal details = (RoomsModal) getIntent().getSerializableExtra("Details");
        if(details!=null){

            Toast.makeText(this, "Details...", Toast.LENGTH_SHORT).show();
//            imgView_TWO.setImageResource(details.getImage());
            iv_home.setImageResource(details.getImg());
//            textView_Name.setText(details.getName());
//            textView_Course.setText(details.getCourse());

        }else {
            Toast.makeText(this, "Empty Details...", Toast.LENGTH_SHORT).show();
        }

    }

    public void onClick(View view) {
        super.onBackPressed();
    }
}