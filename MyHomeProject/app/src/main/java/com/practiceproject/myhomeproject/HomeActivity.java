package com.practiceproject.myhomeproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.android.material.navigation.NavigationView;
import com.practiceproject.myhomeproject.modalClass.RoomsModal;
import com.practiceproject.myhomeproject.rvAdapter.RvAdapter;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    Spinner spinner_home;
    ImageView iv_toolbar;
    ArrayList<String> arrayList;
    RecyclerView rv_home;
    ArrayList<RoomsModal> list;
    RvAdapter adapter;
    NavigationView naigationview_home;
    DrawerLayout drawerlayout_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        iv_toolbar = findViewById(R.id.iv_toolbar);
        spinner_home = findViewById(R.id.spinner_home);
        rv_home = findViewById(R.id.rv_home);
        drawerlayout_home = findViewById(R.id.drawerlayout_home);
        naigationview_home = findViewById(R.id.naigationview_home);

        //---Spinner items-------------------------------
        arrayList = new ArrayList<>();
        arrayList.add("Select house type");
        arrayList.add("3BHK");
        arrayList.add("2BHK");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                HomeActivity.this, android.R.layout.simple_spinner_item, arrayList);
        spinner_home.setAdapter(arrayAdapter);
        //-----------------------------------------------

        spinner_home.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //String item = parent.getItemAtPosition(position).toString();
                rv_home.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
                list = new ArrayList<>();
                if (position == 1) {

                    list.add(new RoomsModal(R.drawable.room1, "3456 Calgary Alberta, Canada",
                            "3 Bedrooms", "12000", "2 Bathrooms"));
                    list.add(new RoomsModal(R.drawable.room2, "3456 Calgary Alberta, Canada",
                            "3 Bedrooms", "15000", "2 Bathrooms"));
                    list.add(new RoomsModal(R.drawable.room3, "3456 Calgary Alberta, Canada",
                            "3 Bedrooms", "14000", "2 Bathrooms"));

                } else if (position == 2) {

                    list.add(new RoomsModal(R.drawable.room1, "3456 Calgary Alberta, USA",
                            "2 Bedrooms", "8000", "1 Bathrooms"));
                    list.add(new RoomsModal(R.drawable.room2, "3456 Calgary Alberta, USA",
                            "2 Bedrooms", "10000", "2 Bathrooms"));
                    list.add(new RoomsModal(R.drawable.room3, "3456 Calgary Alberta, USA",
                            "2 Bedrooms", "7000", "1 Bathrooms"));

                }
                adapter = new RvAdapter(HomeActivity.this, list);
                rv_home.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //-----Drawer--------------------------------------------
        iv_toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerlayout_home.openDrawer(GravityCompat.START);
            }
        });

        //naigationview_home.setItemIconTintList(null);
        //naigationview_home.setItemIconSize(50);

        naigationview_home.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //drawer Automatically close after user click any drawer items
                drawerlayout_home.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (drawerlayout_home.isDrawerOpen(GravityCompat.START)) {
            drawerlayout_home.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed(); //activity close/App close
        }
    }

}