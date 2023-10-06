package com.botmnavviewapp.jsongetexample.UsingRecyclerDemoOne;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.botmnavviewapp.jsondemoexample.R;
import com.botmnavviewapp.jsongetexample.UsingRecyclerDemoOne.modalclass.UsersDataList;
import com.squareup.picasso.Picasso;

public class SecondActivityOne extends AppCompatActivity {

    TextView textview_dummy_name, textview_dummy_email;
    ImageView imageview_dummy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_one);

        imageview_dummy = findViewById(R.id.imageview_dummy);
        textview_dummy_name = findViewById(R.id.textview_dummy_name);
        textview_dummy_email = findViewById(R.id.textview_dummy_email);

        //---------------------------------------------------------------------------------------------------------
        //-----receiving temp response
        //String url = getIntent().getStringExtra("Img");
//        Picasso.get().load(getIntent().getStringExtra("Img"))
//                .placeholder(R.drawable.man_5).into(imageview_dummy);
//        textview_dummy_name.setText(getIntent().getStringExtra("Name"));
//        textview_dummy_email.setText(getIntent().getStringExtra("Email"));
        //-----------------------------------------------------------------------------------------------------------

        UsersDataList usersDataList = (UsersDataList) getIntent().getSerializableExtra("Details");
        if(usersDataList!=null){
            Toast.makeText(this, "Details...", Toast.LENGTH_SHORT).show();
            Picasso.get().load(usersDataList.getAvatar()).placeholder(R.drawable.man_5).into(imageview_dummy);
            textview_dummy_name.setText(usersDataList.getFirst_name()+" "+usersDataList.getLast_name());
            textview_dummy_email.setText(usersDataList.getEmail());

        }else{
//            Toast.makeText(this, "Details...", Toast.LENGTH_SHORT).show();
        }

    }
}