package com.practiceproject.myhomeproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;

public class GoogleMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Marker marker = null;
    EditText et_userLocation;
    Button btn_saveLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map);

        et_userLocation = findViewById(R.id.et_userLocation);
        btn_saveLocation = findViewById(R.id.btn_saveLocation);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btn_saveLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GoogleMapActivity.super.onBackPressed();
                String data = et_userLocation.getText().toString().trim();
                Intent intent = new Intent();
                intent.putExtra("address", data);
                //setResult() to send data back to Activity1.
                setResult(123, intent);
                finish();
            }
        });

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap; //map is ready

        LatLng ddnLatLng = new LatLng(30.316496, 78.032188);
        marker = mMap.addMarker(new MarkerOptions()
                .position(ddnLatLng)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        //.title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ddnLatLng));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ddnLatLng)); //move map to dehradun
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ddnLatLng, 16f));//zoom-in map
        //Getting Data from Location(Geocoder)
        Geocoder geocoder = new Geocoder(this);
        try {
            //maxResults - nearest places -> if mxResult 5 then, nearest by 0,1,2,3,4 places/location
            //ArrayList<Address> => bec. more than 1 address if you set maxResult = 5
            ArrayList<Address> arrAdr = (ArrayList<Address>) geocoder.getFromLocation(ddnLatLng.latitude, ddnLatLng.longitude, 1);
            Log.d("Addr", arrAdr.get(0).getAddressLine(0)); //index 0 => bec. it can more than one line of address
            et_userLocation.setText(arrAdr.get(0).getAddressLine(0));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                //Toast.makeText(MainActivity.this, "Map clicked : "+latLng.latitude, Toast.LENGTH_SHORT).show();
                marker.setPosition(latLng);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18f));
                //Getting Data from Location(Geocoder)
                Geocoder geocoder = new Geocoder(GoogleMapActivity.this);
                try {
                    //maxResults - nearest places -> if maxResult 5 then, nearest 0,1,2,3,4 places
                    ArrayList<Address> arrAdr = (ArrayList<Address>) geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                    Log.d("Addr", arrAdr.get(0).getAddressLine(0));
                    et_userLocation.setText(arrAdr.get(0).getAddressLine(0));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    public void onClick(View view) {
        super.onBackPressed();
    }

}