package com.sharedprefapp.adapterexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class SpinnerActivity extends AppCompatActivity {

    Spinner spinner_item;

    String arrColors[] = {"Select any color", "Red", "Yellow", "Orange", "Black", "Pink", "Blue", "White"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner_item = findViewById(R.id.spinner_item);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, arrColors);
        spinner_item.setAdapter(arrayAdapter);

        spinner_item.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position>0){
                    Toast.makeText(SpinnerActivity.this, "Clicked : " + arrColors[position], Toast.LENGTH_SHORT).show();
                    //Toast.makeText(SpinnerActivity.this, "Clicked : " + position + "position", Toast.LENGTH_SHORT).show();
                    //showToast(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//       Error - setOnItemClickListener() can't be used with spinner.
//        spinner_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(SpinnerActivity.this, "Selected : " + position, Toast.LENGTH_SHORT).show();
//            }
//        });
    }

//    public void showToast(int pos){
//        Toast.makeText(SpinnerActivity.this, "Clicked : "+pos, Toast.LENGTH_SHORT).show();
//    }

}