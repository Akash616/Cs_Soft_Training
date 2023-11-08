package com.broadcastreceiver.broadcastreceiverexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.broadcastreceiver.broadcastreceiverexample.airplanemodechangereceiver.AirplaneModeFragment;
import com.broadcastreceiver.broadcastreceiverexample.networkchangereceiver.NetworkChangeFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().add(R.id.mainContainer, new NetworkChangeFragment()).commit();

    }

}