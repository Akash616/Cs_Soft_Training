package com.bottomnavbarapp.bottomnavigationbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bnv_one;
    TextView textview_dashboard, textview_profile, textview_users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bnv_one = findViewById(R.id.bnv_one);

        textview_dashboard = findViewById(R.id.textview_dashboard);
        textview_profile = findViewById(R.id.textview_profile);
        textview_users = findViewById(R.id.textview_users);

        bnv_one.setOnNavigationItemSelectedListener(this);
        loadFragment(new Dashbord_Fragment());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.textview_dashboard:
                fragment = new Dashbord_Fragment();
                break;
            case R.id.textview_profile:
                fragment = new UsersFragment();
                break;
            case R.id.textview_users:
                fragment = new Profile_Fragment();
                break;
        }
        if (fragment != null) {
            loadFragment(fragment);
        }
        return true;
    }

    void loadFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.linearlayout_one, fragment).commit();
    }

}