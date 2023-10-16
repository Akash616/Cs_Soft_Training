package com.jetpacknavigationcomponent.navigationcomponentexample;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class HomeFragment extends Fragment {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    NavController navController;
    NavHostFragment navHostFragment;
    TextView tv_textTile;
    BottomNavigationView bottom_nav;
    Button btn_nextScreen;
    FrameLayout fragmentLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        drawerLayout = view.findViewById(R.id.drawerLayout);
        navigationView = view.findViewById(R.id.navigationView);
        tv_textTile = view.findViewById(R.id.tv_textTile);
        bottom_nav = view.findViewById(R.id.bottom_nav);
        btn_nextScreen = view.findViewById(R.id.btn_nextScreen);
        fragmentLayout = view.findViewById(R.id.fragmentLayout);

        view.findViewById(R.id.iv_imageMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        //NavigationView (Drawer) icon color fix
        navigationView.setItemIconTintList(null);

        navHostFragment = (NavHostFragment) requireActivity().getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();

//        NavigationUI.setupWithNavController(navigationView, navController);//NavigationView(Drawer)
//        NavigationUI.setupWithNavController(bottom_nav, navController); //BottomNavView

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                tv_textTile.setText(navDestination.getLabel()); //appbar title change
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                if (id == R.id.myAccountFragment) {
                    loadFragment(new MyAccountFragment());
                    closeDrawer();
                } else if (id == R.id.myRequestFragment) {
                    loadFragment(new MyRequestFragment());
                    closeDrawer();
                } else if (id == R.id.rateFragment){
                    loadFragment(new RateFragment());
                    closeDrawer();
                }

                return true;
            }
        });

        btn_nextScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.nextScreenFragment);
            }
        });

        loadFragment(new MyAccountFragment());
        bottom_nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                if (id == R.id.myAccountFragment) {
                    loadFragment(new MyAccountFragment());
                } else if (id == R.id.myRequestFragment) {
                    loadFragment(new MyRequestFragment());
                } else if (id == R.id.rateFragment){
                    loadFragment(new RateFragment());
                }

                return true;
            }
        });

        return view;
    }

    private void closeDrawer() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentLayout, fragment);
        fragmentTransaction.commit();
    }

}