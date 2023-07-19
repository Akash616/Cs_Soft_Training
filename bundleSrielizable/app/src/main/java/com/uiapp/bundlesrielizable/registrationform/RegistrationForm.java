package com.uiapp.bundlesrielizable.registrationform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.uiapp.bundlesrielizable.R;

import java.util.regex.Pattern;

public class RegistrationForm extends AppCompatActivity {

    EditText et_name, et_age, et_email, et_country, et_phone, et_password;
    Button btn_createaccount;
    RadioButton rb_male, rb_female, rb_other;
    CheckBox cb_android, cb_flutter, cb_ios;

    //for password field we don't have predefined Patterns, instead we will put our own
    // into this class, as a constant.
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +     //string start
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    //"(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{6,}" +               //at least 6 characters
                    //".{6,8}" +               //at least 6 characters and 8 maximum character.
                    "$");                   //end of string

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);

        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        et_email = findViewById(R.id.et_email);
        et_country = findViewById(R.id.et_country);
        et_phone = findViewById(R.id.et_phone);
        et_password = findViewById(R.id.et_password);

        btn_createaccount = findViewById(R.id.btn_createaccount);

        rb_male = findViewById(R.id.rb_male);
        rb_female = findViewById(R.id.rb_female);
        rb_other = findViewById(R.id.rb_other);

        cb_android = findViewById(R.id.cb_android);
        cb_flutter = findViewById(R.id.cb_flutter);
        cb_ios = findViewById(R.id.cb_ios);

        btn_createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Validation
                if (et_name.getText().toString().isEmpty() && et_age.getText().toString().isEmpty() &&
                        et_email.getText().toString().isEmpty() && et_country.getText().toString().isEmpty() &&
                        et_phone.getText().toString().isEmpty() && et_password.getText().toString().isEmpty()) {
                    Toast.makeText(RegistrationForm.this, "Fill all details...", Toast.LENGTH_SHORT).show();
                    return;
                } else if (et_name.getText().toString().isEmpty()) {
                    Toast.makeText(RegistrationForm.this, "Enter Name", Toast.LENGTH_SHORT).show();
                    return;
                } else if (et_age.getText().toString().isEmpty()) {
                    Toast.makeText(RegistrationForm.this, "Enter Age", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!validateEmail()) {
                    Toast.makeText(RegistrationForm.this, "Email is not valid", Toast.LENGTH_SHORT).show();
                    return;
                } else if (et_country.getText().toString().isEmpty()) {
                    Toast.makeText(RegistrationForm.this, "Enter Country Name", Toast.LENGTH_SHORT).show();
                    return;
                } else if (et_phone.getText().toString().isEmpty()) {
                    Toast.makeText(RegistrationForm.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!validatePassword()) {
                    Toast.makeText(RegistrationForm.this, "Password is not valid", Toast.LENGTH_SHORT).show();
                    return;
                } else {

                    Intent intent = new Intent(RegistrationForm.this, RegistrationDetailsActivity.class);

                    //----------modal class object-----------------------------------
                    DetailsModalClass details = new DetailsModalClass();
                    details.setName(et_name.getText().toString());
                    details.setAge(Integer.parseInt(et_age.getText().toString()));
                    details.setEmail(et_email.getText().toString());
                    details.setCountry(et_country.getText().toString());
                    details.setPhone(et_phone.getText().toString());
                    details.setPassword(et_password.getText().toString());

                    //---------bundle object-----------------------------------------
                    Bundle bundle = new Bundle();
//                    bundle.putString("Name", et_name.getText().toString());
//                    bundle.putInt("Age", Integer.parseInt(et_age.getText().toString()));
//                    bundle.putString("Email", et_email.getText().toString());
//                    bundle.putString("Country", et_country.getText().toString());
//                    bundle.putString("Phone", et_phone.getText().toString());
//                    bundle.putString("Password", et_password.getText().toString());
                    //-----------modal class use (only one key use)--------------------
                    bundle.putSerializable("Details", details); //details error ? -> implements Serializable
                    intent.putExtras(bundle);
                    //------getting value from radio buttons-------------------------
                    String gender = "";
                    if (rb_male.isChecked()) {
                        gender = rb_male.getText().toString(); //text value
                    } else if (rb_female.isChecked()) {
                        gender = rb_female.getText().toString();
                    } else if (rb_other.isChecked()) {
                        gender = rb_other.getText().toString();
                    }
                    bundle.putString("Gender", gender);
                    //-------getting value from checkbox buttons---------------------
                    String interest = "";
                    if(cb_android.isChecked()){
                        interest += " - "+cb_android.getText().toString()+"\n";
                    }
                    if (cb_flutter.isChecked()) {
                        interest += " - "+cb_flutter.getText().toString()+"\n";
                    }
                    if (cb_ios.isChecked()) {
                        interest += " - "+cb_ios.getText().toString()+"\n";
                    }
                    //bundle.putString("Interest", interest);
                    //intent.putExtras(bundle);

                    startActivity(intent);

                }

            }
        });

    }

    public boolean validateEmail() {
        String emailInput = et_email.getText().toString().trim(); //trim() -> all leading and trailing space removed

        if (emailInput.isEmpty()) {
            et_email.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            //for email address predefined patterns we can use,
            // regex - regular expression ->
//            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
//                    "\\@" +
//                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
//                    "(" +
//                        "\\." +
//                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
//                    ")+"
            et_email.setError("Please enter a valid email address");
            return false;
        } else {
            et_email.setError(null);
            return true;
        }
    }

    public boolean validatePassword() {
        String passwordInput = et_password.getText().toString().trim();

        if (passwordInput.isEmpty()) {
            et_password.setError("Field can't be empty'");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            et_password.setError("Password too weak");
            //explain what characters exactly, your password has to contain
            //otherwise user will confuse.
            return false;
        } else {
            et_password.setError(null);
            return true;
        }
    }


}