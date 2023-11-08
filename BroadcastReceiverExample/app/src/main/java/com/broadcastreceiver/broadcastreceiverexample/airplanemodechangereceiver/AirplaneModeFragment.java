package com.broadcastreceiver.broadcastreceiverexample.airplanemodechangereceiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.broadcastreceiver.broadcastreceiverexample.R;
public class AirplaneModeFragment extends Fragment {

    AirplaneModeChangeReceiver airplaneModeChangeReceiver = new AirplaneModeChangeReceiver();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_airplane_mode, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        //Registering a BroadcastReceiver
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        requireActivity().registerReceiver(airplaneModeChangeReceiver, intentFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        requireActivity().unregisterReceiver(airplaneModeChangeReceiver);
    }


}