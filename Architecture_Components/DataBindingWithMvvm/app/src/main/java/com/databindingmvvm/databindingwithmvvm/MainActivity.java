package com.databindingmvvm.databindingwithmvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.database.DatabaseUtils;
import android.os.Bundle;

import com.databindingmvvm.databindingwithmvvm.databinding.ActivityMainBinding;
import com.databindingmvvm.databindingwithmvvm.models.Customer;
import com.databindingmvvm.databindingwithmvvm.viewmodels.Mainviewmodel;

public class MainActivity extends AppCompatActivity {

    Mainviewmodel mainviewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding mainxml = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mainviewmodel = new ViewModelProvider(this).get(Mainviewmodel.class);

        mainxml.setCustomer(mainviewmodel.getCustomer());

    }
}