package com.broadcastreceiver.broadcastreceiverexample.networkchangereceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class NetworkChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo(); //"android.permission.ACCESS_NETWORK_STATE"
        if (activeNetwork != null && activeNetwork.isConnected()) {
            
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                Toast.makeText(context, "Wifi enabled", Toast.LENGTH_SHORT).show();
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                Toast.makeText(context, "Mobile data enabled", Toast.LENGTH_SHORT).show();
            }
            
        } else {

            Toast.makeText(context, "No internet is available", Toast.LENGTH_SHORT).show();
            
        }

    }
}
