package com.uiapp.bundlesrielizable.registrationform;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.uiapp.bundlesrielizable.R;

public class RegistrationDetailsActivity extends AppCompatActivity {

    TextView tv_registrationdetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_details);

        tv_registrationdetail = findViewById(R.id.tv_registrationdetail);

//        Bundle bundle = getIntent().getExtras();
//        if(bundle != null) {
//
//            String name = bundle.getString("Name");
//            int age = bundle.getInt("Age");
//            String email = bundle.getString("Email");
//            String country = bundle.getString("Country");
//            String phone = bundle.getString("Phone");
//            //String password =  bundle.getString("Password");

//            tv_registrationdetail.setText("Name : "+name+"\nGender : "+bundle.getString("Gender")+
//                    "\nAge : "+age+"\nEmail : "+email+"\nCountry : "+country+"\nInterested Languages : \n"+
//                    bundle.getString("Interest")+"Phone : "+phone+
//                    "\nPassword : "+bundle.getString("Password"));
//
//        }

        //-------------------Modal class-----------------------
        DetailsModalClass detail = (DetailsModalClass) getIntent().getSerializableExtra("Details");
        if(detail != null){
            String name = detail.getName();
            int age = detail.getAge();
            String email = detail.getEmail();
            String country = detail.getCountry();
            String phone = detail.getPhone();

            tv_registrationdetail.setText("Name : "+name+"\nGender : "+age+
                    "\nAge : "+age+"\nEmail : "+email+"\nCountry : "+country+"\nInterested Languages : \n"+
                    country+"Phone : "+phone+
                    "\nPassword : "+detail.getPassword());

        }

    }
}