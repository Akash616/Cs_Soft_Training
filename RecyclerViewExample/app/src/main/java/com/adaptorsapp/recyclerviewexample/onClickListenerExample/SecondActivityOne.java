package com.adaptorsapp.recyclerviewexample.onClickListenerExample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.adaptorsapp.recyclerviewexample.R;
import com.adaptorsapp.recyclerviewexample.onClickListenerExample.modalClass.ModalClass;

public class SecondActivityOne extends AppCompatActivity {

    ImageView imgView_TWO;
    TextView textView_Name, textView_Course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_one);

        imgView_TWO = findViewById(R.id.imgView_TWO);
        textView_Name = findViewById(R.id.textView_Name);
        textView_Course = findViewById(R.id.textView_Course);

//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null) {
//            imgView_TWO.setImageResource(bundle.getInt("Image"));
//            String name = bundle.getString("Name");
//            textView_Name.setText(name);
//            textView_Course.setText(bundle.getString("Course"));
//        } else {
//            Toast.makeText(this, "Empty Details...", Toast.LENGTH_SHORT).show();
//        }

        ModalClass details = (ModalClass) getIntent().getSerializableExtra("Details");
        if(details!=null){

            Toast.makeText(this, "Details...", Toast.LENGTH_SHORT).show();
            imgView_TWO.setImageResource(details.getImage());
            textView_Name.setText(details.getName());
            textView_Course.setText(details.getCourse());

        }else {
            Toast.makeText(this, "Empty Details...", Toast.LENGTH_SHORT).show();
        }

    }
}