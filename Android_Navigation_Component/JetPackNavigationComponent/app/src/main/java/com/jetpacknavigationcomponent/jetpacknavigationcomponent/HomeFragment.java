package com.jetpacknavigationcomponent.jetpacknavigationcomponent;

import static androidx.navigation.ui.NavigationUI.setupActionBarWithNavController;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.service.credentials.Action;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragment extends Fragment {

    Button btn_view_balance, btn_send_money, btn_transaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        btn_view_balance = view.findViewById(R.id.btn_view_balance);
        btn_send_money = view.findViewById(R.id.btn_send_money);
        btn_transaction = view.findViewById(R.id.btn_transaction);

//        // Get the navigation host fragment from this Activity
//        NavHostFragment navHostFragment =
//                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_container);
//        // Instantiate the navController using the NavHostFragment
//        NavController navController = navHostFragment.getNavController();

        btn_view_balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // navController.navigate(R.id.viewBalanceFragment);
                //NavHostFragment.findNavController().navigate(R.id.viewBalanceFragment);
                //Navigation.findNavController(view).navigate(R.id.viewBalanceFragment); //1 way

                //Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_viewBalanceFragment); //2 way

                //3 way recommended to navigate between destinations is to use the Safe Args Gradle plugin, type safe with bundle data passing
                NavDirections action = HomeFragmentDirections.actionHomeFragmentToViewBalanceFragment();
                Navigation.findNavController(view).navigate(action);
            }
        });

        //problem: data send through bundle, but not type safe way
        btn_send_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // navController.navigate(R.id.chooseReceiverFragment);
                //Navigation.findNavController(view).navigate(R.id.chooseReceiverFragment); //1 way

                //Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_chooseReceiverFragment); //2 way

                //3 way recommended to navigate between destinations is to use the Safe Args Gradle plugin, type safe with bundle data passing
                NavDirections action = HomeFragmentDirections.actionHomeFragmentToChooseReceiverFragment();
                Navigation.findNavController(view).navigate(action);
            }
        });

        btn_transaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // navController.navigate(R.id.viewTransactionsFragment);
               // Navigation.findNavController(view).navigate(R.id.viewTransactionsFragment); //1 way

                //Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_viewTransactionsFragment); //2 way

                //3 way recommended to navigate between destinations is to use the Safe Args Gradle plugin, type safe with bundle data passing
                NavDirections action = HomeFragmentDirections.actionHomeFragmentToViewTransactionsFragment();
                Navigation.findNavController(view).navigate(action);
            }
        });

        return view;
    }

}