package com.uiapp.bundlesrielizable.howto.imple.modalclass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.uiapp.bundlesrielizable.R;

public class ThirdActivity extends AppCompatActivity {

    EditText et_name, et_age, et_email, et_password;
    Button btn_click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        btn_click = findViewById(R.id.btn_click);

        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Validation
                if (et_name.getText().toString().isEmpty() && et_age.getText().toString().isEmpty() &&
                        et_email.getText().toString().isEmpty() && et_password.getText().toString().isEmpty()) {
                    Toast.makeText(ThirdActivity.this, "Fill All Details...", Toast.LENGTH_SHORT).show();
                    return;
                } else if (et_name.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter Name", Toast.LENGTH_SHORT).show();
                    return;
                } else if (et_age.getText().toString().isEmpty()) { //et_age.getText().toString() != null => repeating toast
                    Toast.makeText(getApplicationContext(), "Please Enter Age", Toast.LENGTH_SHORT).show();
                    return;
                } else if (et_email.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                } else if (et_password.getText().toString().isEmpty()) {
                    Toast.makeText(ThirdActivity.this, "Please Enter Password", Toast.LENGTH_LONG).show();
                    return;
                } else {

                    Intent intent = new Intent(ThirdActivity.this, FourthActivity.class);

                    //Modal class object
                    StudentModalClass student = new StudentModalClass();
                    //student.setName("Akash");//static
                    student.setName(et_name.getText().toString()); //setName() -> setter method
                    student.setAge(Integer.parseInt(et_age.getText().toString()));

                    Bundle bundle = new Bundle();
                    //bundle.putString("Name", et_name.getText().toString());
                    bundle.putString("Name", student.getName()); //getName() -> getter method
                    //bundle.putInt("Age", Integer.parseInt(et_age.getText().toString()));
                    bundle.putInt("Age",student.getAge());
                    bundle.putString("Email", et_email.getText().toString());
                    bundle.putString("Password", et_password.getText().toString());

                    intent.putExtras(bundle);

                    startActivity(intent);

                }

            }
        });

    }
}