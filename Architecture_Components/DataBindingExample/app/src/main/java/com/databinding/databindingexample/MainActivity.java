package com.databinding.databindingexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.databinding.databindingexample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //java or xml ko Binding class say connect kar diya
        ActivityMainBinding mainxml = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //mainxml.t1.setText("I am changed now"); now dont't access like this

        //Automatically Binding class creates getter() and setter() method, uski help say initialize karanga.
        //mainxml.setFname("Akash Gupta");  //This is primitive way

        //--------Model class-------------------------------------------------------------------------------------
        Product p =  new Product("ASUS");
        //now, setter() method belongs to ActivityMainBinding class
        mainxml.setProduct(p); //p is object

    }
}