package com.practicemapproject.googlemapexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Marker marker = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap; //map is ready

        LatLng ddnLatLng = new LatLng(30.316496, 78.032188);
        MarkerOptions markerOptions = new MarkerOptions()
                .position(ddnLatLng);
        //.title("Dehradun");
        marker = mMap.addMarker(markerOptions); //marker is a overlay on google maps
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ddnLatLng)); //move map to dehradun
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ddnLatLng, 16f));//zoom-in map (limited value 16f to 20f)
        //--------------Overlays in maps--------------------------------------------------
        //circle
//        mMap.addCircle(new CircleOptions()
//                .center(ddnLatLng)
//                .radius(1000) //radius in meter
//                .fillColor(Color.GREEN)
//                .strokeColor(Color.DKGRAY)); //border color
//
//        //Polygon
//        mMap.addPolygon(new PolygonOptions()
//                .add(new LatLng(30.316496, 78.032188),
//                        new LatLng(30.316496, 80.032188),
//                        new LatLng(32.316496, 81.032188),
//                        new LatLng(33.316496, 83.032188),
//                        new LatLng(30.316496, 78.032188))
//                .fillColor(Color.YELLOW)
//                .strokeColor(Color.BLUE));
//
//        //Image add
//        mMap.addGroundOverlay(new GroundOverlayOptions()
//                .position(ddnLatLng, 1000f, 1000f)
//                .image(BitmapDescriptorFactory.fromResource(R.drawable.man_5))
//                .clickable(true));

        //Add marker/update marker
//        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//            @Override
//            public void onMapClick(@NonNull LatLng latLng) {
//                //Toast.makeText(MainActivity.this, "Map clicked : "+latLng.latitude, Toast.LENGTH_SHORT).show();
//                marker.setPosition(latLng);
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16f));
//            }
//        });

//        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
//            @Override
//            public boolean onMarkerClick(@NonNull Marker marker) {
//                Toast.makeText(MainActivity.this, " "+marker.getTitle(), Toast.LENGTH_SHORT).show();
//                return true;
//            }
//        });

        //Map type change - Hybrid, normal, satellite, terrain..
        //by default normal
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                mMap.addMarker(new MarkerOptions().position(latLng));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16f));
                Geocoder geocoder = new Geocoder(MainActivity.this);
                try {
                    //maxResults - nearest places -> if mxResult 5 then, nearest 0,1,2,3,4 places
                    ArrayList<Address> arrAdr = (ArrayList<Address>) geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                    Log.d("Addr", arrAdr.get(0).getAddressLine(0));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        //Getting Data from Location(Geocoder)
//        Geocoder geocoder = new Geocoder(this);
//        try {
//            //maxResults - nearest places -> if mxResult 5 then, nearest by 0,1,2,3,4 places/location
//            ArrayList<Address> arrAdr = (ArrayList<Address>) geocoder.getFromLocation(30.316496, 78.032188, 1);
//            Log.d("Addr", arrAdr.get(0).getAddressLine(0)); //index 0 => bec. it can more than one line of address
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

    }

}